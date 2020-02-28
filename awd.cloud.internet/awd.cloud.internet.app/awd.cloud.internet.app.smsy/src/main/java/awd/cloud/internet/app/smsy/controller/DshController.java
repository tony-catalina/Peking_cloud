package awd.cloud.internet.app.smsy.controller;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import awd.cloud.internet.app.smsy.model.SmsyModel;
import awd.cloud.internet.app.smsy.utils.PagerResult;
import awd.cloud.internet.app.smsy.utils.QueryParam;
import awd.cloud.internet.app.smsy.utils.TermType;
import awd.framework.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Maps;

import awd.cloud.internet.app.smsy.api.AwdApi;
import awd.cloud.internet.app.smsy.model.UnitModel;
import awd.cloud.internet.app.smsy.utils.ResponseMessage;
/**
 * @Description
 * @Author WS
 * @Date 2019-07-12 20:36
 */
@Controller
@RequestMapping("/dsh")
public class DshController {

    @Autowired
    private AwdApi awdApi;
	
    @PostMapping("/getPcsList")
    @ResponseBody
    public Map<String, Object> getPcsList(HttpServletRequest request) {
    	Map<String, Object> map = Maps.newHashMap();
    	String phase = request.getParameter("phase");
//    	if(StringUtils.isNullOrEmpty(phase)){
//    		return null;
//		}
    	ResponseMessage<List<UnitModel>> result;
		try {
			result = awdApi.queryPcs();
			if (result.getStatus() == 200) {
				map.put("total", result.getResult().size());
				map.put("data", result.getResult());
			}else {
				map.put("total", 0);
				map.put("data", new ArrayList<UnitModel>(0));
			}
		} catch (Exception e) {
			map.put("total", 0);
			map.put("data", new ArrayList<UnitModel>(0));
		}
    	return map;
    }


	@PostMapping("/getDshrycx")
	@ResponseBody
	public Map<String, Object> getDshrycx(HttpServletRequest request) {
		Map<String, Object> map = Maps.newHashMap();
		ResponseMessage<List<SmsyModel>> result;
		try {
			result = awdApi.getDshrycx();
			if (result.getStatus() == 200) {
				map.put("total", result.getResult().size());
				map.put("data", result.getResult());
			}else {
				map.put("total", 0);
				map.put("data", new ArrayList<UnitModel>(0));
			}
		} catch (Exception e) {
			map.put("total", 0);
			map.put("data", new ArrayList<UnitModel>(0));
		}
		return map;
	}







}
