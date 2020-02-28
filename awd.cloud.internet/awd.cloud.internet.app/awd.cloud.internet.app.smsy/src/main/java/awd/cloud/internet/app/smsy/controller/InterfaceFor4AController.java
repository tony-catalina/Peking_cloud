package awd.cloud.internet.app.smsy.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;

import awd.cloud.internet.app.smsy.api.AwdApi;
import awd.cloud.internet.app.smsy.model.UnitModel;
import awd.cloud.internet.app.smsy.model.UserDetailModel;
import awd.cloud.internet.app.smsy.utils.ResponseMessage;
import awd.framework.common.utils.StringUtils;

@Component
@RequestMapping("/interface")
public class InterfaceFor4AController {

    @Autowired
    private AwdApi awdApi;
    
    
    @PostMapping("/init")
    @ResponseBody
    public ResponseMessage<?> init(HttpServletRequest request) {
    	String type = request.getParameter("type");
    	ResponseMessage<?> res = new ResponseMessage();
    	if (!StringUtils.isNullOrEmpty(type)) {
    		res = awdApi.init();
    		return res;
		}else {
			return ResponseMessage.error("error");
		}
    }
    
    
	@PostMapping("/getRootOrg")
    @ResponseBody
    public Map<String, Object> getRootOrg(HttpServletRequest request) {
		ResponseMessage<Map<String, Object>> result = awdApi.getRootOrg();
		Map<String, Object> map = Maps.newHashMap();
		if (result.getStatus() == 200) {
			map.put("total", 1);
			map.put("data", result.getResult());
		}else {
			map.put("total", 0);
			map.put("data", new ArrayList<UnitModel>(0));
		}
		return map;
    }
	
	@PostMapping("/getOrgsByParentCode")
	@ResponseBody
	public Map<String, Object> getOrgsByParentCode(HttpServletRequest request) {
		String rootCode = request.getParameter("rootCode");
		ResponseMessage<List<Map<String, Object>>> result = awdApi.getOrgsByParentCode(rootCode);
		Map<String, Object> map = Maps.newHashMap();
		if (result.getStatus() == 200) {
			map.put("total", result.getResult().size());
			map.put("data", result.getResult());
		}else {
			map.put("total", 0);
			map.put("data", new ArrayList<UnitModel>(0));
		}
		return map;
	}
	
	@PostMapping("/getUserByOrgCode")
	@ResponseBody
	public Map<String, Object> getUserByOrgCode(HttpServletRequest request) {
		String orgCode = request.getParameter("orgCode");
		ResponseMessage<List<Map<String, Object>>> result = awdApi.getUserByOrgCode(orgCode);
		Map<String, Object> map = Maps.newHashMap();
		if (result.getStatus() == 200) {
			map.put("total", result.getResult().size());
			map.put("data", result.getResult());
		}else {
			map.put("total", 0);
			map.put("data", new ArrayList<UnitModel>(0));
		}
		return map;
	}
	
	@PostMapping("/getOrgInfo")
	@ResponseBody
	public Map<String, Object> getOrgInfo(HttpServletRequest request) {
		String orgCode = request.getParameter("orgCode");
		ResponseMessage<Map<String, Object>> result = awdApi.getOrgInfo(orgCode);
		System.err.println("awdApi---"+awdApi);
		System.err.println("result---"+result);
		Map<String, Object> map = Maps.newHashMap();
		if (result.getStatus() == 200) {
			map.put("total", 1);
			map.put("data", result.getResult());
		}else {
			map.put("total", 0);
			map.put("data", new ArrayList<UnitModel>(0));
		}
		return map;
	}
	
	@PostMapping("/getUserInfo")
	@ResponseBody
	public Map<String, Object> getUserInfo(HttpServletRequest request) {
		String userCode = request.getParameter("userCode");
		ResponseMessage<Map<String, Object>> result = awdApi.getUserInfo(userCode);
		Map<String, Object> map = Maps.newHashMap();
		if (result.getStatus() == 200) {
			map.put("total", 1);
			map.put("data", result.getResult());
		}else {
			map.put("total", 0);
			map.put("data", new ArrayList<UnitModel>(0));
		}
		return map;
	}
	
	@PostMapping("/getResourceByAppCode")
	@ResponseBody
	public Map<String, Object> getResourceByAppCode(HttpServletRequest request) {
		ResponseMessage<List<Map<String, Object>>> result = awdApi.getResourceByAppCode();
		Map<String, Object> map = Maps.newHashMap();
		if (result.getStatus() == 200) {
			map.put("total", result.getResult().size());
			map.put("data", result.getResult());
		}else {
			map.put("total", 0);
			map.put("data", new ArrayList<UnitModel>(0));
		}
		return map;
	}
	
	@PostMapping("/getRoleByAppCode")
	@ResponseBody
	public Map<String, Object> getRoleByAppCode(HttpServletRequest request) {
		ResponseMessage<List<Map<String, Object>>> result = awdApi.getRoleByAppCode();
		Map<String, Object> map = Maps.newHashMap();
		if (result.getStatus() == 200) {
			map.put("total", result.getResult().size());
			map.put("data", result.getResult());
		}else {
			map.put("total", 0);
			map.put("data", new ArrayList<UnitModel>(0));
		}
		return map;
	}
	
	@PostMapping("/getOrgList")
	@ResponseBody
	public Map<String, Object> getOrgList(HttpServletRequest request) {
		String type = request.getParameter("type");
		System.err.println("type---"+type);
		Map<String, Object> map = Maps.newHashMap();
		ResponseMessage<List<Map<String, Object>>> result;
		try {
			result = awdApi.getOrgList(type);
			if (result.getStatus() == 200) {
				map.put("total", result.getResult().size());
				map.put("data", result.getResult());
			}else {
				map.put("total", 0);
				map.put("data", new ArrayList<>(0));
			}
		} catch (Exception e) {
			map.put("total", 0);
			map.put("data", new ArrayList<>(0));
		}
		return map;
	}
	
	@PostMapping("/getUserModelFrom4A")
	@ResponseBody
	public Map<String, Object> getUserModelFrom4A(@ModelAttribute UserDetailModel initialUser, HttpServletRequest request) {
		
		//先用userid 查询数据库
		String userid = initialUser.getUserid();
        Map<String, Object> map = Maps.newHashMap();
        UserDetailModel finalDetailUser = new UserDetailModel();
		try {
			ResponseMessage<UserDetailModel> re = awdApi.getUserModelFrom4A(initialUser);
			if (re.getStatus() == 200) {
				finalDetailUser = re.getResult();
			} else {
				finalDetailUser = initialUser;
			}
			System.err.println("finalDetailUser---"+JSON.toJSONString(finalDetailUser));
			map.put("total", 1);
			map.put("data", finalDetailUser);
			HttpSession session = request.getSession();
			Object userinfo = session.getAttribute("userinfo");
			if (!StringUtils.isNullOrEmpty(userinfo)) {
				session.removeAttribute("userinfo");
			}
			session.setAttribute("userinfo", finalDetailUser);
			session.setMaxInactiveInterval(24 * 60 * 60);
			return map;
			
		} catch (Exception e) {
			System.err.println("没有从4A获取到 "+initialUser.getName()+" 人员相关信息");
			e.printStackTrace();
		}
		map.put("total", 0);
		map.put("data", new ArrayList<UserDetailModel>(0));
		System.err.println("没有从4A获取到 "+initialUser.getName()+" 人员相关信息");
		return map;
	}
	
	
	/**
	 * 先查询 系统缓存，假如没有，就从接口查询用户信息，并重新放入缓存
	 */
	public void name(String userid) {
		
	}
	
	
}
