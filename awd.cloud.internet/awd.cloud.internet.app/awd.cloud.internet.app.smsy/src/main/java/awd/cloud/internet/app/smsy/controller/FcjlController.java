package awd.cloud.internet.app.smsy.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Maps;

import awd.cloud.internet.app.smsy.api.AwdApi;
import awd.cloud.internet.app.smsy.model.FcjlModel;
import awd.cloud.internet.app.smsy.model.SmsyModel;
import awd.cloud.internet.app.smsy.model.UserinfoModel;
import awd.cloud.internet.app.smsy.utils.PagerResult;
import awd.cloud.internet.app.smsy.utils.QueryParam;
import awd.cloud.internet.app.smsy.utils.ResponseMessage;
import awd.cloud.internet.app.smsy.utils.Sort;
import awd.cloud.internet.app.smsy.utils.TermType;
import awd.framework.common.utils.DateTimeUtils;
import awd.framework.common.utils.JSONUtil;

@Component
@RequestMapping("/fcjl")
public class FcjlController {

    @Autowired
    private AwdApi awdApi;
    
	@PostMapping("/getFcjlList")
    @ResponseBody
	public Map<String, Object> getClglList(HttpServletRequest request) {
		String jsbh = request.getParameter("jsbh");
		String fczt = request.getParameter("fczt");
        
        Map<String, Object> map = Maps.newHashMap();
        QueryParam queryParam = new QueryParam();
        queryParam.and("jsbh", TermType.eq, jsbh)
	        .and("fczt", TermType.eq, fczt)
        	.and("state", TermType.eq, "R2");
        queryParam.setPaging(false);
        //queryParam.setOrder("DESC");
		
		List<Sort> sorts = new ArrayList<>();
		Sort sort = new Sort();
		sort.setName("id");
		sort.setOrder("desc");
		sorts.add(sort);
		queryParam.setSorts(sorts);
		 
        ResponseMessage<PagerResult<FcjlModel>> result;
        try {
            result = awdApi.queryFcjl(queryParam);
            if (result.getStatus() == 200) {
                map.put("total", result.getResult().getTotal());
                map.put("data", result.getResult().getData());
            }else {
                map.put("total", 0);
                map.put("data", new ArrayList<FcjlModel>(0));
            }
        } catch (Exception e) {
            map.put("total", 0);
            map.put("data", new ArrayList<FcjlModel>(0));
        }
        return map;
	}
	
	
	@PostMapping("/getFcjlListGroupByJsbh")
	@ResponseBody
	public Map<String, Object> getFcjlListGroupByJsbh(HttpServletRequest request) {
		String dwbh = request.getParameter("dwbh");
		String fczt = request.getParameter("fczt");
		
		Map<String, Object> map = Maps.newHashMap();
		QueryParam queryParam = new QueryParam();
		queryParam.and("badw", TermType.like, dwbh)
		.and("fczt", TermType.eq, fczt)
		.and("state", TermType.eq, "R2");
		queryParam.setPaging(false);
		//queryParam.setOrder("DESC");
		List<Sort> sorts = new ArrayList<>();
		Sort sort = new Sort();
		sort.setName("id");
		sort.setOrder("desc");
		sorts.add(sort);
		queryParam.setSorts(sorts);
		
		ResponseMessage<PagerResult<FcjlModel>> result;
		try {
			result = awdApi.queryFcjl(queryParam);
			if (result.getStatus() == 200) {
				Map<String, List<FcjlModel>> groupByJsbh = result.getResult().getData().stream()
						.collect(Collectors.groupingBy(FcjlModel::getJsbhString));
				map.put("total", result.getResult().getTotal());
				map.put("data", groupByJsbh);
			}else {
				map.put("total", 0);
				map.put("data", new ArrayList<FcjlModel>(0));
			}
		} catch (Exception e) {
			map.put("total", 0);
			map.put("data", new ArrayList<FcjlModel>(0));
		}
		return map;
	}
	
	@PostMapping("/addFcjl")
	@ResponseBody
	public ResponseMessage<String> addClgl(HttpServletRequest request,FcjlModel fcjlModel){
		ResponseMessage<String> result = awdApi.addFcjl(fcjlModel);
		return result;
	}
	
	//监管单位操作接受或者拒收人员之后，确认本次发车是否结束
	@PostMapping("/updateFcjlAfterDsm")
	@ResponseBody
	public ResponseMessage<String> updateFcjlAfterDsm(HttpServletRequest request,
			@RequestParam("fcuuid") String fcuuid,
			@RequestParam("badw") String badw,
			@RequestParam("fczt") String fczt){
		
		ResponseMessage<String> re = awdApi.updateFcjlAfterDsm(fcuuid,badw, fczt);
		System.err.println("re--"+JSONUtil.toJson(re));
		return re;
	}
	
	@PostMapping("/updateFcjl")
	@ResponseBody
	public ResponseMessage<String> updateFcjl(HttpServletRequest request,@RequestParam("id") String id,FcjlModel fcjlModel){
		ResponseMessage<String> result = awdApi.updateFcjl(id,fcjlModel);
		return result;
	}
	
	@PostMapping("/deleteByid")
	@ResponseBody
	public ResponseMessage<String> deleteByid(HttpServletRequest request,@RequestParam("id") String id){
		ResponseMessage<String> result = awdApi.deleteFcjlByid(id);
		return result;
	}
	
}
