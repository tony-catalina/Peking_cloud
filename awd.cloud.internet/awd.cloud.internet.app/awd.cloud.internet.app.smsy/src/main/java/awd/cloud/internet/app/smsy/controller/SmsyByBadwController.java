package awd.cloud.internet.app.smsy.controller;

import awd.cloud.internet.app.smsy.api.AwdApi;
import awd.cloud.internet.app.smsy.model.SmsyModel;
import awd.cloud.internet.app.smsy.model.UserinfoModel;
import awd.cloud.internet.app.smsy.utils.PagerResult;
import awd.cloud.internet.app.smsy.utils.QueryParam;
import awd.cloud.internet.app.smsy.utils.ResponseMessage;
import awd.cloud.internet.app.smsy.utils.TermType;
import awd.framework.common.utils.DateTimeUtils;
import awd.framework.common.utils.JSONUtil;
import awd.framework.common.utils.StringUtils;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/smsy")
public class SmsyByBadwController {
	@Autowired
	private AwdApi awdApi;

	/**
	 * 办案单位根据阶段获取 获取上门收押信息
	 * 
	 * @param request
	 * @return
	 */
	@PostMapping("/getSmsyDsm")
	@ResponseBody
	public Map<String, Object> getSmsyDsm(HttpServletRequest request) {
		UserinfoModel userinfo = (UserinfoModel) request.getSession().getAttribute("userinfo");
		String type = request.getParameter("phase");
		Map<String, Object> map = Maps.newHashMap();
		QueryParam qParam = new QueryParam();
		qParam.setPaging(false);
		qParam.and("badw", userinfo.getDwbh());	
		if (!StringUtils.isNullOrEmpty(type)) {
			qParam.and("phase", TermType.in, type);
		} 
		Calendar theCa = Calendar.getInstance();
        theCa.setTime(new Date());
        theCa.add(theCa.DATE, -30);
        
    	String end=DateTimeUtils.format(Calendar.getInstance().getTime(), DateTimeUtils.YEAR_MONTH_DAY)+" 23:59:59";
    	String start=DateTimeUtils.format(theCa.getTime(), DateTimeUtils.YEAR_MONTH_DAY)+" 00:00:00";
    	qParam.and("createtime", TermType.lte, end).
    	and("createtime", TermType.gte,start);
		ResponseMessage<PagerResult<SmsyModel>> result;
		try {
			result = awdApi.querySmsy(qParam);
			Map<String, List<SmsyModel>> groupBy = result.getResult().getData().stream()
					.collect(Collectors.groupingBy(SmsyModel::getGyjsString));
			if (result.getStatus() == 200) {
				map.put("total", result.getResult().getTotal());
				map.put("data", groupBy);
			} else {
				map.put("total", 0);
				map.put("data", new ArrayList<SmsyModel>(0));
			}
		} catch (Exception e) {
			map.put("total", 0);
			map.put("data", new ArrayList<SmsyModel>(0));
		}
		
		return map;
	}
	
	/**
	  *  上门收押登记人员文书信息
	 * @param request
	 * @return
	 */
	@PostMapping("/addTzsm")
    @ResponseBody
    public Map<String, Object> addTzsm(HttpServletRequest request) {
    	
    	HttpSession session = request.getSession();
		UserinfoModel userinfo = (UserinfoModel) session.getAttribute("userinfo");
		
		String badw = request.getParameter("badw");
		String badwdz = request.getParameter("badwdz");
		String gyjs = request.getParameter("gyjs");
		String xyrxm = request.getParameter("xyrxm");
		
		SmsyModel smsyModel = new SmsyModel();
		smsyModel.setBadw(badw);
		smsyModel.setBadwdz(badwdz);
		smsyModel.setGyjs(gyjs);
		smsyModel.setXyrxm(xyrxm);
		
		/* 以下是生成uuid*/
		long timestamp = new Date().getTime();
    	String uuid = String.valueOf(timestamp) + Integer.valueOf(xyrxm.toString().hashCode());
    	System.err.println(uuid);
    	
    	/* 以下是获取上传的文件*/
    	List<MultipartFile> photoList = new ArrayList<>();
    	CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
    	if (commonsMultipartResolver.isMultipart(request)) {
    		System.err.println("-----是多媒体文件------");
            //转换request
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            
            //下面是同名文件
            photoList = multiRequest.getFiles("file");
            for (MultipartFile file : photoList) {
            	System.err.println("-----循环上传图片------");
            	awdApi.upload(file, uuid);
			}
        } 
    	
    	smsyModel.setFilenum(photoList.size());
    	smsyModel.setPhase("1");
    	smsyModel.setState("R2");
    	smsyModel.setUuid(uuid);
    	smsyModel.setCreator(userinfo.getWxh());
    	ResponseMessage<String> result;
    	
    	Map<String, Object> map = Maps.newHashMap();
    	try {
			result = awdApi.pcsAddSm(smsyModel);
			if(result.getStatus()==200) {
				map.put("total", 0);
				map.put("data", "1");
			}else {
				map.put("total", 0);
				map.put("data", "0");
			}
		} catch (Exception e) {
			map.put("total", 0);
			map.put("data", "0");
		}
    	
    	return map;
    }
	
	
	@PostMapping("/smsydelete")
    @ResponseBody
	public ResponseMessage<String> smsydelete(String id,HttpServletRequest request){
		HttpSession session = request.getSession();
		UserinfoModel userinfo = (UserinfoModel) session.getAttribute("userinfo");
		System.err.println(id);
		System.err.println(awdApi);
		return awdApi.smdelete(id);
	}
	
	
    /**
     * 根据发车记录获取上门信息
     * @param request
     * @return
     */
    @PostMapping("/getBadwSmsyListByFcjl")
    @ResponseBody
    public Map<String, Object> getKssSmsyListByFcjl(HttpServletRequest request) {
    	String fcuuid = request.getParameter("fcuuid");
    	String badw = request.getParameter("badw");
    	QueryParam queryParam = new QueryParam();
    	queryParam.setPaging(false);
    	
    	queryParam.and("badw", TermType.eq, badw)
    	.and("fcuuid", TermType.eq, fcuuid)
    	.and("phase", TermType.eq, "4")
    	.and("state", TermType.eq, "R2");
    	
    	Calendar theCa = Calendar.getInstance();
    	theCa.setTime(new Date());
    	theCa.add(theCa.DATE, -30);
    	
    	String end=DateTimeUtils.format(Calendar.getInstance().getTime(), DateTimeUtils.YEAR_MONTH_DAY)+" 23:59:59";
    	String start=DateTimeUtils.format(theCa.getTime(), DateTimeUtils.YEAR_MONTH_DAY)+" 00:00:00";
    	queryParam.and("createtime", TermType.lte, end).
    	and("createtime", TermType.gte,start);
    	Map<String, Object> map = Maps.newHashMap();
    	ResponseMessage<PagerResult<SmsyModel>> smsyResult;
    	try {
    		smsyResult = awdApi.querySmsy(queryParam);
    		if (smsyResult.getStatus() == 200) {
    			
    			map.put("total", smsyResult.getResult().getTotal());
    			map.put("data", smsyResult.getResult().getData());
    		}else {
    			map.put("total", 0);
    			map.put("data", new ArrayList<SmsyModel>(0));
    		}
    	} catch (Exception e) {
    		map.put("total", 0);
    		map.put("data", new ArrayList<SmsyModel>(0));
    	}
    	return map;
    }
 
	
}
