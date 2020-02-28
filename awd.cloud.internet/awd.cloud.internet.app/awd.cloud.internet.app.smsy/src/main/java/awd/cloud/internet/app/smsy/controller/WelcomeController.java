package awd.cloud.internet.app.smsy.controller;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import awd.cloud.internet.app.smsy.api.AwdApi;
import awd.cloud.internet.app.smsy.model.AdminModel;
import awd.cloud.internet.app.smsy.model.FileModel;
import awd.cloud.internet.app.smsy.model.SmsyModel;
import awd.cloud.internet.app.smsy.model.UnitModel;
import awd.cloud.internet.app.smsy.model.UserinfoModel;
import awd.cloud.internet.app.smsy.utils.GeturlPic;
import awd.cloud.internet.app.smsy.utils.KssDictionay;
import awd.cloud.internet.app.smsy.utils.PagerResult;
import awd.cloud.internet.app.smsy.utils.QueryParam;
import awd.cloud.internet.app.smsy.utils.ResponseMessage;
import awd.cloud.internet.app.smsy.utils.TermType;
import awd.framework.common.utils.HttpClientUtil;
import awd.framework.common.utils.StringUtils;
import cn.hutool.json.JSONUtil;

import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description
 * @Author WS
 * @Date 2019-07-12 20:36
 */
@Controller
public class WelcomeController {
	
	@Value("${awd.ftp.ftphost}")
	private String ftphost;
	
	@Value("${server.port}")
	private  String port;
	
	@Value("${awd.image.cache}")
	private  String imagepath;

	@Autowired
    private AwdApi awdApi;
	
    @RequestMapping("/")
    public String index(Model model,HttpServletRequest request) {
    	String state=request.getParameter("state");
    	System.err.println("state---"+state);
    	/*
    	if("ios".equals(state)) {
    		return "index";
    	}else {
    		return "index_android";
    	}
        */
    	return "index";
    }
    
    
    public List<String> getPhotoByid(String id) {
    	ResponseMessage<List<FileModel>> result = awdApi.queryFilesByid(id);
    	List<String> strList = new ArrayList<>(0);
    	if (result.getStatus() == 200) {
    		
    		System.out.println(result);
    		List<FileModel> fileList = result.getResult();
    		if(fileList!=null&&fileList.size()>0) {
    			fileList.forEach(file->{
        			String imgPath = "/getPic.jpg?id="+file.getId();       			
        			strList.add(imgPath);
        		});
    		}
    		
		}
    	if (StringUtils.isNullOrEmpty(strList)) {
			return new ArrayList<>(0);
		}
    	return strList;
	}
    
    @RequestMapping("/pcs/ryxx")
    @ResponseBody
    public List<String> getPcsryxx(Model model,HttpServletRequest request){
    	String id = request.getParameter("id");
    	List<String> filePathList = this.getPhotoByid(id);
    	return filePathList;
    }
    
    @RequestMapping("/admin")
	@ResponseBody
    public ResponseMessage<AdminModel> getadmin(QueryParam param,HttpServletRequest request){
    	String jsbh=request.getParameter("jsbh");
    	String password=request.getParameter("password");
    	param.setPaging(false);
    	param.and("jsbh", jsbh).and("password", password);
    	ResponseMessage<PagerResult<AdminModel>> responeresult=awdApi.queryAdmin(param);
    	if(responeresult.getStatus()==200) {
    		if(responeresult.getResult().getData()!=null&&responeresult.getResult().getData().size()>0) {
    			return ResponseMessage.ok(responeresult.getResult().getData().get(0));
    		}
    	}
    	return ResponseMessage.ok(null);
    }

	@RequestMapping("/logintype")
	@ResponseBody
	public String getsmsytype(HttpServletRequest request){
		HttpSession session = request.getSession();
		UserinfoModel userinfo = (UserinfoModel) session.getAttribute("userinfo");
		return userinfo.getType();
	}
	
	@RequestMapping(value="/http-get",method=RequestMethod.GET)
	@ResponseBody
	public String http_get(String url){
		System.err.println("http-get");
		System.err.println(url);
		String result = HttpClientUtil.doGet(url);
		System.err.println(result);
		return result;
	}
	
	@RequestMapping(value="/http-post",method=RequestMethod.POST)
	@ResponseBody
	public String http_post(@RequestParam("url") String url,@RequestParam("json") String json){
		System.err.println("http-post");
		System.err.println(url);
		System.err.println(json);
		String result = null;
		if (json != null) {
			result = HttpClientUtil.doPostJson(url,json);
		}else {
			result = HttpClientUtil.doPost(url);
		}
		System.err.println(result);
		return result;
	}
	
	@RequestMapping(value="/redirect",method=RequestMethod.GET)
	public void http_redirect(String url,HttpServletResponse respone){
		try {
			respone.sendRedirect(url);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

    @RequestMapping(value = "/deleteLocalCache",method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage<String> deleteLocalCache(HttpServletRequest request){
    	HttpSession session = request.getSession();
    	session.removeAttribute("userinfo");
    	return ResponseMessage.ok();
    }
    
    @RequestMapping(value = "/deleteRemoteCache",method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage<String> deleteUserCacheByUserid(@RequestParam("userid") String userid, HttpServletRequest request){
    	
    	ResponseMessage<String> re = awdApi.deleteUserCacheByUserid(userid);
    	if (re.getStatus() == 200) {
    		HttpSession session = request.getSession();
    		session.removeAttribute("userinfo");
		}
    	return re;
    }
    
}
