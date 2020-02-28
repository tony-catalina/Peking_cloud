package awd.cloud.internet.web.manager.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;

import awd.cloud.internet.web.manager.api.AwdApi;
import awd.cloud.internet.web.manager.models.TzggModel;
import awd.cloud.internet.web.manager.utils.KssDictionay;
import awd.cloud.internet.web.manager.utils.PagerResult;
import awd.cloud.internet.web.manager.utils.ResponseMessage;
import awd.framework.common.core.param.TermType;
import awd.framework.common.datasource.orm.core.param.QueryParam;
import awd.framework.common.utils.StringUtils;

@RestController
@RequestMapping("/tzgg")
public class TzggController {
	
	@Autowired		
	AwdApi awdapi;
	
	
	@PostMapping("/saveTzgg")
	@ResponseBody
	public ResponseMessage<String> saveOrUpdateTzgg(TzggModel model){
		model.setState("R2");
		model.setCreatetime(new Date());
		model.setGzrq(new Date());
		System.err.println(JSON.toJSONString(model)+"**");
		return awdapi.tzggSaveOrUpdate(model);
	}

	@GetMapping("/getTzgg")
	public Map<String,Object> getTzgg(HttpServletRequest request){
		String ggrq = request.getParameter("ggrq");
		String dwmc = request.getParameter("dwmc");
		QueryParam param  = new QueryParam();
		param.and("state", "R2");
		if(!StringUtils.isNullOrEmpty(ggrq)) {
			param.and("gzrq", ggrq);
		}
		if(!StringUtils.isNullOrEmpty(dwmc)) {
			String jsbh=null;
			Map<String, Object> map = KssDictionay.getDictionary().getMap();
			 	Set<Entry<String, Object>> entries = map.entrySet();
			 	Iterator<Entry<String, Object>> iteratorMap = entries.iterator();
			 	StringBuffer sb = new StringBuffer();
			 	while (iteratorMap.hasNext()){
			 		Entry<String, Object> next = iteratorMap.next();
			 		System.err.println(next.getValue()+"");
			 		if(next.getValue().toString().contains(dwmc)) {
			 			sb.append(next.getKey()+",");
			 		}
			 		}
			 	if(sb.toString().endsWith(",")) {
			 		 jsbh = sb.toString().substring(0, sb.length()-1);
			 	}
			 	param.and("dwbh", TermType.in,jsbh);
		}
		
		String page = request.getParameter("page");
		String rows = request.getParameter("limit");
		int pageIndex = 0;
		int pageSize = 10;
		try {
			pageIndex = Integer.valueOf(page).intValue() - 1;
			pageSize = Integer.valueOf(rows).intValue();
		} catch (NumberFormatException e) {
			// e.printStackTrace();
			System.err.println("分页参数出错！");
		} finally {
			param.setPageIndex(pageIndex);
			param.setPageSize(pageSize);
		}
		System.err.println(JSON.toJSONString(param)+"********");
		ResponseMessage<PagerResult<TzggModel>> res = awdapi.getTzgg(param);
		Map<String,Object> map = new HashMap<String, Object>();
		System.err.println(JSON.toJSONString(res));
		map.put("code", 0);
		map.put("msg", "");
		map.put("count", res.getResult().getTotal());
		map.put("data", res.getResult().getData());
		
		return map;
		
	}
}
