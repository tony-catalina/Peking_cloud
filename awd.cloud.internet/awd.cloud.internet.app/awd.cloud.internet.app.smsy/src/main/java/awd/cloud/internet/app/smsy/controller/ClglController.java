package awd.cloud.internet.app.smsy.controller;

import java.util.ArrayList;
import java.util.Map;

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
import awd.cloud.internet.app.smsy.model.ClglModel;
import awd.cloud.internet.app.smsy.model.UserinfoModel;
import awd.cloud.internet.app.smsy.utils.PagerResult;
import awd.cloud.internet.app.smsy.utils.QueryParam;
import awd.cloud.internet.app.smsy.utils.ResponseMessage;
import awd.cloud.internet.app.smsy.utils.TermType;

@Component
@RequestMapping("/clgl")
public class ClglController {

    @Autowired
    private AwdApi awdApi;
    
	
	@PostMapping("/getClglList")
    @ResponseBody
	public Map<String, Object> getClglList(HttpServletRequest request) {
		
		String jsbh = "000000000";
		try {
			HttpSession session = request.getSession();
			UserinfoModel userinfo = (UserinfoModel) session.getAttribute("userinfo");
			if ("1".equals(userinfo.getType())) {
				jsbh = userinfo.getDwbh();
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
        
        Map<String, Object> map = Maps.newHashMap();
        QueryParam queryParam = new QueryParam();
        queryParam.and("jsbh", TermType.eq, jsbh)
        	.and("state", TermType.eq, "R2");
        queryParam.setPaging(false);
        
        ResponseMessage<PagerResult<ClglModel>> result;
        try {
            result = awdApi.queryClgl(queryParam);
            if (result.getStatus() == 200) {
                map.put("total", result.getResult().getTotal());
                map.put("data", result.getResult().getData());
            }else {
                map.put("total", 0);
                map.put("data", new ArrayList<ClglModel>(0));
            }
        } catch (Exception e) {
            map.put("total", 0);
            map.put("data", new ArrayList<ClglModel>(0));
        }
        return map;
	}
	
	@PostMapping("/addClgl")
	@ResponseBody
	public ResponseMessage<String> addClgl(HttpServletRequest request,ClglModel clglModel){
		
		ResponseMessage<String> result = awdApi.addClgl(clglModel);
		
		return result;
	}
	
	@PostMapping("/deleteByid")
	@ResponseBody
	public ResponseMessage<String> deleteByid(HttpServletRequest request,@RequestParam("id") String id){
		ResponseMessage<String> result = awdApi.deleteClglByid(id);
		return result;
	}
	
}
