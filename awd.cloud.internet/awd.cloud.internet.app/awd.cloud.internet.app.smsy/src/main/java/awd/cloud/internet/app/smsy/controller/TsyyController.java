package awd.cloud.internet.app.smsy.controller;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Maps;

import awd.cloud.internet.app.smsy.api.AwdApi;
import awd.cloud.internet.app.smsy.model.FjsysModel;
import awd.cloud.internet.app.smsy.model.SmsyModel;
import awd.cloud.internet.app.smsy.model.TsyyModel;
import awd.cloud.internet.app.smsy.model.UnitModel;
import awd.cloud.internet.app.smsy.model.UserinfoModel;
import awd.cloud.internet.app.smsy.utils.PagerResult;
import awd.cloud.internet.app.smsy.utils.QueryParam;
import awd.cloud.internet.app.smsy.utils.ResponseMessage;
import awd.cloud.internet.app.smsy.utils.Sort;
import awd.cloud.internet.app.smsy.utils.TermType;
import awd.framework.common.utils.DateTimeUtils;
import awd.framework.common.utils.JSONUtil;
import awd.framework.common.utils.StringUtils;
import cn.hutool.json.JSONObject;

/**
 * @Description
 * @Author WS
 * @Date 2019-07-12 20:36
 */
@Controller
@RequestMapping("/tsyy")
public class TsyyController {

	@Value("${awd.ftp.ftphost}")
	private  String ftphost;
	
    @Autowired
    private AwdApi awdApi;
	
    @PostMapping("/saveTsyy")
    @ResponseBody
    public ResponseMessage<?> saveTsyy(HttpServletRequest request,TsyyModel tsyy,BindingResult bindingResult){
    	HttpSession session = request.getSession();
		UserinfoModel userinfo = (UserinfoModel) session.getAttribute("userinfo");
		String fjsl=request.getParameter("fjsl");
		System.err.println(JSON.toJSONString(userinfo));
		//查询监所名称
		QueryParam param = new QueryParam();
		param.and("dwbh", userinfo.getDwbh());
		 ResponseMessage<PagerResult<UnitModel>> re= awdApi.queryUnit(param);
		 if(re.getResult().getTotal() != 0) {
			 tsyy.setLsdw(re.getResult().getData().get(0).getDwmc());
			 tsyy.setDwdh("0010-5541221");
		 }
			tsyy.setLsxm(userinfo.getUsername());
			tsyy.setLszh(userinfo.getJh());
			tsyy.setLsdw(userinfo.getDwbhString());
			tsyy.setSjh(userinfo.getPhone());
            tsyy.setState("R2");
            tsyy.setRevokes("0");
            tsyy.setIslshj("1");
            tsyy.setHjlx("1");
            tsyy.setCreatetime(new Date());
            System.err.println(JSON.toJSONString(tsyy));
            ResponseMessage<String> rs= awdApi.save(tsyy);
            System.err.println(rs.getStatus());
            if(rs.getStatus() == 200) {
            	//将监室数量减一
            	System.err.println("*******开始保存剩余房间*********");
            	System.err.println(String.valueOf(Integer.parseInt(fjsl)-1));
            	awdApi.updateFjByJsbh(String.valueOf(Integer.parseInt(fjsl)-1), tsyy.getJsbh());
            	System.err.println("*******开始保存剩余房间结束*********");
            	//保存成功后向ftp发送数据
            	String tsyyStr = JSON.toJSONString(tsyy);
            	Map<String, Object> map = Maps.newHashMap();
            	this.httpPostRequest(tsyyStr);
            	return ResponseMessage.ok("保存成功");
            }else{
            	return ResponseMessage.error("保存失败");
            }
    		
    }

    @GetMapping("/cloud_unit")
    @ResponseBody
    public ResponseMessage<PagerResult<UnitModel>> queryUnit(UnitModel unitModel,BindingResult bindingResult){
        QueryParam queryParam=new QueryParam();
        queryParam.setPaging(false);
        return awdApi.queryUnit(queryParam);
    }
    
    @GetMapping("/getTsyy")
    @ResponseBody
    public Map<String,Object> getTsyy(HttpServletRequest request){
    	HttpSession session = request.getSession();
    	UserinfoModel userinfo = (UserinfoModel) session.getAttribute("userinfo");
    	  QueryParam  queryParam= new QueryParam();
    	  String yyid= request.getParameter("yyid");
    	  if(!StringUtils.isNullOrEmpty(yyid)) {
    		  queryParam.and("yyid", yyid);
    	  }else {
    	  queryParam.setPaging(false);
    	  queryParam.and("lsxm", userinfo.getUsername());
    	  }
    	  queryParam.setSort("yyrq");
    	  queryParam.setOrder("desc");
  		  String sortName = "yyrq";
  	      String orderBy ="desc";
  	        List<Sort> sorts = new ArrayList<>();
  	        Sort sort = new Sort();
  	        	sort.setName("yyrq");
  	        	sort.setOrder("desc");
  	        sorts.add(sort);
  	      queryParam.setSorts(sorts);
	  	    Calendar theCa = Calendar.getInstance();
	        theCa.setTime(new Date());
	        theCa.add(theCa.DATE, -30);
	        
	    	String end=DateTimeUtils.format(Calendar.getInstance().getTime(), DateTimeUtils.YEAR_MONTH_DAY)+" 23:59:59";
	    	String start=DateTimeUtils.format(theCa.getTime(), DateTimeUtils.YEAR_MONTH_DAY)+" 00:00:00";
	    	queryParam.and("createtime", TermType.lte, end).
	    	and("createtime", TermType.gte,start);
    	  ResponseMessage<PagerResult<TsyyModel>> rs=awdApi.getTsyy( queryParam);
    	  Map<String,Object> map = new HashMap<>();
    	  Map<String, List<TsyyModel>> groupBy = rs.getResult().getData().stream()
					.collect(Collectors.groupingBy(TsyyModel::getJsbhString));
    	  if(StringUtils.isNullOrEmpty(yyid)) {
    		  map.put("result", groupBy);
    	  }else {
    		  map.put("result", rs.getResult().getData()); 
    	  }
    	  
    	  map.put("status", rs.getStatus());
    	  return  map;
    }
    
    @GetMapping("/getSyfj")
    @ResponseBody
    public   Map<String,Object>  getFjsl(HttpServletRequest request){
   	  String jsbh = request.getParameter("jsbh");
	  QueryParam  queryParam= new QueryParam();
	  queryParam.and("jsbh", jsbh);
	  ResponseMessage<PagerResult<FjsysModel>> rs=awdApi.getsyfj(queryParam);
	  Map<String,Object> map = new HashMap<>();
	  if(rs.getStatus()==200) {
		  map.put("result", rs.getResult().getData().get(0));
    	  map.put("status", rs.getStatus());
	  }    	  
	  return  map;
    }
    
    
    @GetMapping("/getYyslByjs")
    @ResponseBody
    public boolean getYyslByjs(HttpServletRequest request){
     	HttpSession session = request.getSession();
    	UserinfoModel userinfo = (UserinfoModel) session.getAttribute("userinfo");
    	  String jsbh = request.getParameter("jsbh");
    	  QueryParam  queryParam= new QueryParam();
    	  queryParam.and("jsbh", jsbh);
    	  queryParam.and("lsxm", userinfo.getUsername());		
    	  System.err.println("queryParam="+JSON.toJSONString(queryParam));
    	  ResponseMessage<PagerResult<TsyyModel>> rs=awdApi.getTsyy(queryParam);
    	  System.err.println(JSON.toJSONString(rs.getResult().getData()));
    	  if(rs.getResult().getTotal() == 0) {
    		  return true;
    	  }else{
    		  for (TsyyModel model : rs.getResult().getData()) {
				if(model.getState().equals("R2") && model.getRevokes().equals("0")){
					return false;
				}
			}
    	  }
		return true;
    }
    
    
    public  String httpPostRequest(String json) {
		System.err.println(json);
		System.err.println(ftphost+"********");
		String targetURL = ftphost+"/mysql/uploadJsonToFtp";
		try {
            HttpClient client = HttpClients.createDefault();
            HttpPost request = new HttpPost(targetURL);
            List<NameValuePair> nvps = new ArrayList<NameValuePair>();  
            nvps.add(new BasicNameValuePair("json", json));
            UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(nvps, "utf-8");
            request.setEntity(formEntity);
            HttpResponse response = client.execute(request);
            if (response.getStatusLine().getStatusCode() != 200) {
            	System.err.println("接口调用失败！");
			}
        } catch (Exception e) {
            e.printStackTrace();
        }
		return "test";
	}
    
    
}
