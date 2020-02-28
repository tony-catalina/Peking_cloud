package awd.cloud.internet.app.smsy.controller;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;

import awd.cloud.internet.app.smsy.api.AwdApi;
import awd.cloud.internet.app.smsy.model.TzggModel;
import awd.cloud.internet.app.smsy.model.UserinfoModel;
import awd.cloud.internet.app.smsy.utils.PagerResult;
import awd.cloud.internet.app.smsy.utils.QueryParam;
import awd.cloud.internet.app.smsy.utils.ResponseMessage;
import awd.cloud.internet.app.smsy.utils.Sort;

@RestController
@RequestMapping("/tzgg")
public class TzggController {
	
	@Autowired		
	private AwdApi awdapi;
	
	

	@GetMapping("/getTzgg")
	public Map<String,Object> getTzgg(HttpServletRequest request){
		HttpSession session = request.getSession();
		UserinfoModel userinfo = (UserinfoModel) session.getAttribute("userinfo");
		QueryParam param = new QueryParam();
		param.setPaging(false);
		param.setSort("gzrq");
		param.setOrder("desc");
		  String sortName = "gzrq";
	      String orderBy ="desc";
	        
	        List<Sort> sorts = new ArrayList<>();
	        Sort sort = new Sort();
	        	sort.setName("gzrq");
	        	sort.setOrder("desc");
	        sorts.add(sort);
	        param.setSorts(sorts);
		System.err.println(JSON.toJSONString(param)+"****");
		ResponseMessage<PagerResult<TzggModel>> re = awdapi.getTzgg(param);
		Map<String,Object> map =  new HashMap();
		map.put("data",re.getResult().getData());
		return map;
		}
}
