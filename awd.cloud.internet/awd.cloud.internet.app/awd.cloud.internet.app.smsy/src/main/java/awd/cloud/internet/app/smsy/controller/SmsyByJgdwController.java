package awd.cloud.internet.app.smsy.controller;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;

import awd.cloud.internet.app.smsy.api.AwdApi;
import awd.cloud.internet.app.smsy.model.FcjlModel;
import awd.cloud.internet.app.smsy.model.SmsyAndFcjlModel;
import awd.cloud.internet.app.smsy.model.SmsyModel;
import awd.cloud.internet.app.smsy.model.UserinfoModel;
import awd.cloud.internet.app.smsy.utils.PagerResult;
import awd.cloud.internet.app.smsy.utils.QueryParam;
import awd.cloud.internet.app.smsy.utils.ResponseMessage;
import awd.cloud.internet.app.smsy.utils.Sort;
import awd.cloud.internet.app.smsy.utils.TermType;
import awd.framework.common.utils.DateTimeUtils;
/**
 * @Description
 * @Author WS
 * @Date 2019-07-12 20:36
 */
@Controller
@RequestMapping("/smsy")
public class SmsyByJgdwController {

    @Autowired
    private AwdApi awdApi;
	
    @Autowired
    private UnitController unitController;
    
    /**
     * 动态查询不同状态的人员
     * DynamicQuery
     */
    @PostMapping("/getSmsyByDynamicQuery")
    @ResponseBody
    public Map<String, Object> dynamicQuery(HttpServletRequest request) {
    	String badw = "";
    	try {
    		HttpSession session = request.getSession();
    		UserinfoModel userinfo = (UserinfoModel) session.getAttribute("userinfo");
    		System.err.println("userinfo==="+userinfo.getUsername());
    		badw = userinfo.getDwbh();
		} catch (Exception e) {
			badw = "badw";
		}
    	QueryParam queryParam = new QueryParam();
    	queryParam.setPaging(false);
    	queryParam.and("badw", TermType.eq, badw)
    		.and("state", TermType.eq, "R2");
    	String phase = request.getParameter("phase");
    	if (phase.length() > 1) {
    		queryParam.and("phase", TermType.in, phase);
    	}else {
    		queryParam.and("phase", TermType.eq, phase);
		}
    	Map<String, Object> map = Maps.newHashMap();
    	ResponseMessage<PagerResult<SmsyModel>> result;
		try {
			result = awdApi.querySmsy(queryParam);
			if (result.getStatus() == 200) {
				map.put("total", result.getResult().getTotal());
				map.put("data", result.getResult().getData());
			}else {
				map.put("total", 0);
				map.put("data", new ArrayList<SmsyModel>(0));
			}
		} catch (Exception e) {
			//e.printStackTrace();
			map.put("total", 0);
			map.put("data", new ArrayList<SmsyModel>(0));
		}
    	return map;
    }
    
    /**
     * 获取待上门信息
     * @param request
     * @return
     */
    @PostMapping("/getDsmList")
    @ResponseBody
    public Map<String, Object> getDsmList(HttpServletRequest request) {
    	String badw = "";
    	try {
    		HttpSession session = request.getSession();
    		UserinfoModel userinfo = (UserinfoModel) session.getAttribute("userinfo");
    		badw = userinfo.getDwbh();
		} catch (Exception e) {
			badw = "badw";
		}
    	QueryParam queryParam = new QueryParam();
    	queryParam.setPaging(false);
    	queryParam.and("badw", TermType.eq, badw)
    		.and("phase", TermType.eq, "3")
    		.and("state", TermType.eq, "R2");
    	Map<String, Object> map = Maps.newHashMap();
    	ResponseMessage<PagerResult<SmsyModel>> smsyResult;
		try {
			//收押人员信息
			smsyResult = awdApi.querySmsy(queryParam);
			if (smsyResult.getStatus() == 200) {
				List<SmsyModel> list = smsyResult.getResult().getData();
				List<Map<String,Object>> result = new ArrayList<Map<String,Object>>();
				Set<String> gyjsSet = new HashSet<>();
				for(SmsyModel smsy : list) {
					//获取关押监所
					gyjsSet.add(smsy.getGyjs());
				}
				if(gyjsSet.size()>0) {
					for(String s: gyjsSet) {
						Map<String,Object> m = new HashMap<>();
						List<SmsyModel> sm = new ArrayList<>();
						//获取单位名称
						String dwmc = awdApi.getDwmcByDwbhAndType(s, "1").getResult();
						m.put("dwbh",s);
						m.put("dwmc",dwmc);
						for(int i=0;i<list.size();i++) {
							if(list.get(i).getGyjs().equals(s)) {
								sm.add(list.get(i));
							}
						}
						m.put("smsy",sm);
						result.add(m);
					}
				}
				map.put("total", result.size());
				map.put("data", result);
			}else {
				map.put("total", 0);
				map.put("data", new ArrayList<SmsyModel>(0));
			}
    	} catch (Exception e) {
    		//e.printStackTrace();
    		map.put("total", 0);
    		map.put("data", new ArrayList<SmsyModel>(0));
    	}
    	return map;
    }
    
    /**
         * 监管单位获取待审核信息
     * @param request
     * @return
     */
    @PostMapping("/getSmsyList")
    @ResponseBody
    public Map<String, Object> getSmsyList(HttpServletRequest request) {
    	HttpSession session = request.getSession();
		UserinfoModel userinfo = (UserinfoModel) session.getAttribute("userinfo");
		
		String phase=request.getParameter("phase");
    	Map<String, Object> map = Maps.newHashMap();
    	QueryParam queryParam = new QueryParam();
    	queryParam.and("phase",TermType.in ,phase);
    	queryParam.and("state","R2");
    	queryParam.and("gyjs",userinfo.getDwbh());
    	queryParam.setPaging(false);
    	Calendar theCa = Calendar.getInstance();
        theCa.setTime(new Date());
        theCa.add(theCa.DATE, -30);
        
    	String end=DateTimeUtils.format(Calendar.getInstance().getTime(), DateTimeUtils.YEAR_MONTH_DAY)+" 23:59:59";
    	String start=DateTimeUtils.format(theCa.getTime(), DateTimeUtils.YEAR_MONTH_DAY)+" 00:00:00";
    	queryParam.and("createtime", TermType.lte, end).
    	and("createtime", TermType.gte,start);
    	ResponseMessage<PagerResult<SmsyModel>> result;
		try {
			result = awdApi.querySmsy(queryParam);
			Map<String, List<SmsyModel>> groupBy = result.getResult().getData().stream().collect(Collectors.groupingBy(SmsyModel::getBadwString));
			if (result.getStatus() == 200) {
				map.put("total", result.getResult().getTotal());
				map.put("data", groupBy);
			}else {
				map.put("total", 0);
				map.put("data", new ArrayList<SmsyModel>(0));
			}
		} catch (Exception e) {
			//e.printStackTrace();
			map.put("total", 0);
			map.put("data", new ArrayList<SmsyModel>(0));
		}
    	return map;
    }
	
    /**
     * 获取待未通过人员信息
     * @param request
     * @return
     */
    @PostMapping("/getWtgryList")
    @ResponseBody
    public Map<String, Object> getWtgryList(HttpServletRequest request) {
    	String badw = "";
    	try {
    		HttpSession session = request.getSession();
    		UserinfoModel userinfo = (UserinfoModel) session.getAttribute("userinfo");
    		System.err.println("userinfo==="+userinfo.getUsername());
    		badw = userinfo.getDwbh();
		} catch (Exception e) {
			badw = "单位编号未获取到";
		}
    	Map<String, Object> map = Maps.newHashMap();
    	ResponseMessage<List<SmsyModel>> result;
		try {
			System.err.println("badw==="+badw);
			result = awdApi.queryShsb(badw);
			if (result.getStatus() == 200) {
				map.put("total", result.getResult().size());
				map.put("data", result.getResult());
			}else {
				map.put("total", 0);
				map.put("data", new ArrayList<SmsyModel>(0));
			}
		} catch (Exception e) {
			//e.printStackTrace();
			map.put("total", 0);
			map.put("data", new ArrayList<SmsyModel>(0));
		}
    	return map;
    }
    
    
    /**
     * 派出所主动撤回
     * @param request
     * @return
     */
    @PostMapping("/pcsZdchSmsy")
    @ResponseBody
    public Map<String, Object> pcsZdchSmsy(HttpServletRequest request) {
    	String idString = request.getParameter("ids");
    	String chyy = request.getParameter("chyy");
    	System.err.println("idString=="+idString);
    	System.err.println("chyy=="+chyy);
    	//String[] ids = idString.split(",");
    	Map<String, Object> map = Maps.newHashMap();
    	ResponseMessage<Map<String, Object>> result;
    	try {
    		result = awdApi.pcsZdchSmsy(idString,chyy);
    		if (result.getStatus() == 200) {
    			map = result.getResult();
    		}else {
    			map.put("code", 500);
    			map.put("msg", "撤销失败!");
    		}
    	} catch (Exception e) {
    		//e.printStackTrace();
    		map.put("code", 500);
    		map.put("msg", "撤销失败!");
    	}
    	return map;
    }
    
    
	/**
	 * 上门登记
	 */
	@PostMapping("/smSave")
    @ResponseBody
	public ResponseMessage<String> smSave(SmsyModel smsyModel,FcjlModel fcjlModel,HttpServletRequest request){
		String badws = request.getParameter("badws");
		fcjlModel.setId("");
		fcjlModel.setState("R2");
		fcjlModel.setFczt("1");
		fcjlModel.setBadw(badws);
		System.err.println("fcjlModel---"+JSON.toJSONString(fcjlModel));
		
		SmsyAndFcjlModel smsyAndFcjlModel = new SmsyAndFcjlModel();
		smsyAndFcjlModel.setFcjlModel(fcjlModel);
		smsyAndFcjlModel.setSmsyModel(smsyModel);
		String[] ids = smsyModel.getId().split(",");
		String re = "0";
		if (ids.length > 0 ) {
			re = awdApi.updateSmsyList(ids, smsyAndFcjlModel).getResult();
		}
		if (re == "0") {
			ResponseMessage.error("保存失败！");
		}
		return ResponseMessage.ok(re);
	}
    
    /**
     * 根据发车记录获取上门信息
     * @param request
     * @return
     */
    @PostMapping("/getKssSmsyListByFcjl")
    @ResponseBody
    public Map<String, Object> getKssSmsyListByFcjl(HttpServletRequest request) {
    	String fcuuid = request.getParameter("fcuuid");
    	String jsbh = request.getParameter("jsbh");
    	String phase = request.getParameter("phase");
    	QueryParam queryParam = new QueryParam();
    	queryParam.setPaging(false);
    	
    	queryParam.and("gyjs", TermType.eq, jsbh)
    	.and("fcuuid", TermType.eq, fcuuid)
    	.and("phase", TermType.eq, phase)
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
    			Map<String, List<SmsyModel>> groupBy = smsyResult.getResult().getData().stream()
    					.collect(Collectors.groupingBy(SmsyModel::getBadwString));
    			Map<String, Map<String, List<SmsyModel>>> resultMap = Maps.newHashMap();
    			groupBy.forEach((k,v)->{
    				Map<String, List<SmsyModel>> subKeyMap = v.stream().collect(Collectors.groupingBy(SmsyModel::getBadwdz));
    				resultMap.put(k, subKeyMap);
    			});
    			
    			map.put("total", smsyResult.getResult().getTotal());
    			map.put("data", resultMap);
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
