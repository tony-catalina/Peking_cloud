package awd.cloud.internet.web.manager.filter;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class WebHandlerInterceptor implements HandlerInterceptor {
	private static Map<String, Object> global=new HashMap<String, Object>();
    /**
     * controller 执行之前调用
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        System.out.println("------preHandle-----");   //url
        global.put("url", request.getRequestURL());   //method
        global.put("method", request.getMethod());    //ip
        global.put("ip", request.getRemoteAddr());    //user
        return true;
    }

    /**
     * controller 执行之后，且页面渲染之前调用
     */
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception { 
    	if(modelAndView!=null) {
    		if(!StringUtils.isEmpty(modelAndView.getViewName())) {
    			System.out.println("viewname:"+modelAndView.getViewName());
            	modelAndView.getModelMap().addAllAttributes(global);   
            	System.out.println(modelAndView.getModelMap().toString());
                System.out.println("------postHandle-----");
    		}
    		
    	}
    	
    }

    /**
     * 页面渲染之后调用，一般用于资源清理操作
     */
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        System.out.println("------afterCompletion-----");

    }

}
