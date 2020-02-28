package awd.cloud.internet.app.smsy.controller;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Maps;

import awd.cloud.internet.app.smsy.api.AwdApi;
import awd.cloud.internet.app.smsy.model.SmsyModel;
import awd.cloud.internet.app.smsy.model.UserinfoModel;
import awd.cloud.internet.app.smsy.utils.PagerResult;
import awd.cloud.internet.app.smsy.utils.QueryParam;
import awd.cloud.internet.app.smsy.utils.ResponseMessage;
import awd.framework.common.utils.JSONUtil;
import awd.framework.common.utils.StringUtils;
/**
 * @Description
 * @Author WS
 * @Date 2019-07-12 20:36
 */
@Controller
@RequestMapping("/smsy")
public class SmsyPhaseChangeController {

    @Autowired
    private AwdApi awdApi;	
    
    /**
         *  上门接受,拒收，派出所通知上门
     * @param request
     * @return
     */
    @PostMapping("/change")
    @ResponseBody
    public Map<String, Object> kssDsmjs(SmsyModel smsyModel,HttpServletRequest request) {
    	HttpSession session = request.getSession();
		UserinfoModel userinfo = (UserinfoModel) session.getAttribute("userinfo");
    	Map<String, Object> map = Maps.newHashMap();
    	ResponseMessage<String> result;
		String phase = request.getParameter("phase");  
		String chyy = request.getParameter("chyy"); //撤销原因
		String jsly = request.getParameter("jsly");// 拒收原因
		String ids = smsyModel.getId();
		String id[] = ids.split(",");
		
		//接收
		//js 2:法律文书不全 3：可以上门 4：等待收押 5 收押入所
		smsyModel.setPhase(phase);
		if ("5".equals(phase)) {
			smsyModel.setSyrq(new Date());
		}
		if(!StringUtils.isNullOrEmpty(chyy)) {
			smsyModel.setChyy(chyy);
		}
		if(!StringUtils.isNullOrEmpty(jsly)) {
			smsyModel.setJsly(jsly);
		}		
		smsyModel.setUpdator(userinfo.getUsername());
		smsyModel.setUpdatetime(new Date());
		try {
			for(int i=0;i<id.length;i++) {
				smsyModel.setId(id[i]);
				System.err.println("smsyModel---"+JSONUtil.toJson(smsyModel));
				result = awdApi.updateSmsy(id[i], smsyModel);
				if(result.getStatus()==200) {
					map.put("total", 0);
					map.put("data", "1");
				}else {
					map.put("total", 0);
					map.put("data", "0");
				}
			}
		} catch (Exception e) {
			map.put("total", 0);
			map.put("data", "0");
		}
    	return map;
    }

}
