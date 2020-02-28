package awd.cloud.internet.web.manager.controller;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import awd.cloud.internet.web.manager.api.AwdApi;
import awd.cloud.internet.web.manager.models.UserinfoModel;
import awd.framework.common.controller.message.ResponseMessage;
import awd.framework.common.entity.DataStatus;
import awd.framework.common.utils.IPAddressUtil;
import awd.framework.common.utils.web.BusinessException;
import awd.framework.common.utils.web.NotFoundException;
import awd.framework.expands.authorization.Authentication;
import awd.framework.expands.authorization.AuthenticationInitializeService;
import awd.framework.expands.authorization.User;
import awd.framework.expands.authorization.listener.AuthorizationListenerDispatcher;
import awd.framework.expands.authorization.listener.event.AuthorizationBeforeEvent;
import awd.framework.expands.authorization.listener.event.AuthorizationDecodeEvent;
import awd.framework.expands.authorization.listener.event.AuthorizationExitEvent;
import awd.framework.expands.authorization.listener.event.AuthorizationFailedEvent;
import awd.framework.expands.authorization.listener.event.AuthorizationSuccessEvent;
import io.swagger.annotations.ApiParam;

@Controller
public class WelcomeController {	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private AwdApi awdApi;
	@Autowired
	private AuthenticationInitializeService authenticationInitializeService;
	@Autowired
    private AuthorizationListenerDispatcher authorizationListenerDispatcher;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String welcome(ModelMap model,HttpServletRequest request) {
		User user =  Authentication.current().get().getUser();
		
		if(user!=null) {
			System.out.println("登录成功");
			return "admin/index";
		}else {
			System.out.println("未登录");
			return "admin/login";
		}
		
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(ModelMap model,HttpServletRequest request) {
		return "admin/login";
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout( Authentication authentication) {
		authorizationListenerDispatcher.doEvent(new AuthorizationExitEvent(authentication)); 
		return "admin/login";
	}
	
	@RequestMapping(value = "/welcome", method = RequestMethod.GET)
	public String welcome() {
		User user =  Authentication.current().get().getUser();
		return "admin/welcome";
	}
	
	@PostMapping("/submit")
	@ResponseBody
	public Map<String, Object> submit(@RequestParam String username,
            @RequestParam String password,HttpServletRequest request,
             HttpServletResponse respone){
		Map result=new HashMap<String, Object>();
        AuthorizationFailedEvent.Reason reason = AuthorizationFailedEvent.Reason.OTHER;
        Function<String, Object> parameterGetter = request::getParameter;
        try {
            AuthorizationDecodeEvent decodeEvent = new AuthorizationDecodeEvent(username, password, parameterGetter);
            authorizationListenerDispatcher.doEvent(decodeEvent);
            username = decodeEvent.getUsername();
            password = decodeEvent.getPassword();
            AuthorizationBeforeEvent beforeEvent = new AuthorizationBeforeEvent(username, password, parameterGetter);
            authorizationListenerDispatcher.doEvent(beforeEvent);
            UserinfoModel entity=null;
            if("admin".equals(username)) {
            	entity = new UserinfoModel();
            	entity.setId("admin");
                entity.setUsername("admin");
                entity.setPassword("admin");
                entity.setState("R2");
            }            
            if (entity == null) {
                reason = AuthorizationFailedEvent.Reason.USER_NOT_EXISTS;
                throw new NotFoundException("{user_not_exists}");
            }
            if (!entity.getPassword().equals(password)) {
                reason = AuthorizationFailedEvent.Reason.PASSWORD_ERROR;
                throw new BusinessException("{password_error}", 400);
            }
            if (!DataStatus.STATUS_ENABLED.equals(entity.getState())) {
                reason = AuthorizationFailedEvent.Reason.USER_DISABLED;
                throw new BusinessException("{user_is_disabled}", 400);
            }
            request.getSession().setAttribute("userInfo",JSON.toJSONString(entity));
            
            // 验证通过
            Authentication authentication = authenticationInitializeService.initUserAuthorization(entity.getId());
            AuthorizationSuccessEvent event = new AuthorizationSuccessEvent(authentication, parameterGetter);
            int size = authorizationListenerDispatcher.doEvent(event);
            if (size == 0) {
                logger.warn("not found any AuthorizationSuccessEvent,access control maybe disabled!");
            }
            result.put("success", true);
            result.put("message", entity.getId());
            return result;
        } catch (Exception e) {
            AuthorizationFailedEvent failedEvent = new AuthorizationFailedEvent(username, password, parameterGetter, reason);
            failedEvent.setException(e);
            authorizationListenerDispatcher.doEvent(failedEvent);
            throw e;
        }
	}
	
	
	@RequestMapping(value = "/smsy/query", method = RequestMethod.GET)
	public String smsy_query(ModelMap model,HttpServletRequest request) {
		return "admin/smsy/query";
	}
	
	@RequestMapping(value = "/smsy/logquery", method = RequestMethod.GET)
	public String smsy_logquery(ModelMap model,HttpServletRequest request) {
		return "admin/smsy/logquery";
	}
	
	@RequestMapping(value = "/smsy/jsgl", method = RequestMethod.GET)
	public String smsy_jsgl(ModelMap model,HttpServletRequest request) {
		return "admin/smsy/jsgl";
	}
	
	@RequestMapping(value = "/smsy/badw", method = RequestMethod.GET)
	public String smsy_badw(ModelMap model,HttpServletRequest request) {
		return "admin/smsy/badw";
	}
	
	@RequestMapping(value = "/smsy/user", method = RequestMethod.GET)
	public String smsy_user(ModelMap model,HttpServletRequest request) {
		return "admin/smsy/user";
	}
	@RequestMapping(value = "/smsy/gzgz", method = RequestMethod.GET)
	public String gzgz() { 
		return "admin/smsy/gzgz";
	}
	
	
}
