package awd.cloud.internet.app.smsy.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import awd.framework.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Maps;

import awd.cloud.internet.app.smsy.api.AwdApi;
import awd.cloud.internet.app.smsy.model.UnitModel;
import awd.cloud.internet.app.smsy.model.UnitcommonlyModel;
import awd.cloud.internet.app.smsy.utils.PagerResult;
import awd.cloud.internet.app.smsy.utils.QueryParam;
import awd.cloud.internet.app.smsy.utils.ResponseMessage;
import awd.cloud.internet.app.smsy.utils.TermType;

@Component
@RequestMapping("/unit")
public class UnitController {

    @Autowired
    private AwdApi awdApi;
    
	@Cacheable("getDwmc")  
	public String getDwmc(String dwbh,String type) {  
		String dwmc = "";
		try {
			dwmc = awdApi.getDwmcByDwbhAndType(dwbh, type).getResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dwmc;
	}
	
	@PostMapping("/getUnitList")
    @ResponseBody
    public Map<String, Object> getUnitList(HttpServletRequest request) {
        String type = request.getParameter("type");
        Map<String, Object> map = Maps.newHashMap();
        QueryParam queryParam = new QueryParam();
        queryParam.and("type", TermType.eq, type);
        queryParam.and("state", TermType.eq, "R2");
        //queryParam.setPaging(false);
        queryParam.setPageIndex(0);
        queryParam.setPageSize(3000);
        ResponseMessage<PagerResult<UnitModel>> result;
        try {
            result = awdApi.queryUnit(queryParam);
            if (result.getStatus() == 200) {
                map.put("total", result.getResult().getTotal());
                map.put("data", result.getResult().getData());
            }else {
                map.put("total", 0);
                map.put("data", new ArrayList<UnitModel>(0));
            }
        } catch (Exception e) {
            map.put("total", 0);
            map.put("data", new ArrayList<UnitModel>(0));
        }
        map.put("isCommonly", false);
        return map;
    }
	
	@PostMapping("/getCommonlyUnitList")
	@ResponseBody
	public Map<String, Object> getCommonlyUnitList(HttpServletRequest request) {
		String userid = request.getParameter("userid");
		String type = request.getParameter("type");
		Map<String, Object> map = Maps.newHashMap();
		QueryParam queryParam = new QueryParam();
		queryParam.and("userids", TermType.like, "%" + userid + "%");
		queryParam.and("type", TermType.eq, type);
		queryParam.and("state", TermType.eq, "R2");
		//queryParam.setPaging(false);
		queryParam.setPageIndex(0);
		queryParam.setPageSize(3000);
		ResponseMessage<PagerResult<UnitcommonlyModel>> result;
		try {
			result = awdApi.queryCommonlyUnit(queryParam);
			if (result.getStatus() == 200) {
				map.put("total", result.getResult().getTotal());
				map.put("data", result.getResult().getData());
			}else {
				map.put("total", 0);
				map.put("data", new ArrayList<UnitcommonlyModel>(0));
			}
		} catch (Exception e) {
			map.put("total", 0);
			map.put("data", new ArrayList<UnitcommonlyModel>(0));
		}
		map.put("isCommonly", true);
		return map;
	}
	
	@PostMapping("/setCommonlyUnit")
	@ResponseBody
	public ResponseMessage<String> setCommonlyUnit(HttpServletRequest request) {
		String userid = request.getParameter("userid");
		String dwbh = request.getParameter("dwbh");
		String option = request.getParameter("option");
		ResponseMessage<String> result;
		String returnString;
		try {
			result = awdApi.setCommonlyUnit(userid, dwbh, option);
			if (result.getStatus() == 200) {
				returnString = result.getResult();
				return ResponseMessage.ok(returnString);
			}else {
				returnString = "error";
			}
		} catch (Exception e) {
			returnString = "error";
		}
		return ResponseMessage.error(returnString);
	}
	
}
