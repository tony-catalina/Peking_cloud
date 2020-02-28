package awd.cloud.internet.web.manager.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * 综合岗位AOP切面
 * @author ws
 *
 */
@Aspect
@Component
public class AopConfig {

    private final static Logger logger = LoggerFactory.getLogger(AopConfig.class);

    @Pointcut("execution(public * awd.cloud.internet.web.manager.controller.*.*(..))")
    public void log() {
    }

    @Before("log()")
    public void doBefore(JoinPoint joinPoint) {
    	ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        HttpServletResponse response = (HttpServletResponse) attributes.getResponse();
        /* 修改默认'x-frame-options' 值为 'SAMEORIGIN' 允许界面在同域名下的页面中嵌套 */
        //response.setHeader("x-frame-options","SAMEORIGIN");
        //response.setHeader("Access-Control-Allow-Origin",request.getHeader("Origin"));
        //使用HTML模板引擎无需跨域操作
		/*
		response.setHeader("Access-Control-Allow-Origin",request.getHeader("Origin"));
		response.setHeader("Access-Control-Allow-Credentials", "true");
		response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
		response.setHeader("Access-Control-Max-Age", "3600");
		response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
		response.setHeader("Content-Type", "application/json;charset=UTF-8");
		response.setHeader("Accept", "application/json");*/
		


        //url
        logger.info("url={}", request.getRequestURL());

        //method
        logger.info("method={}", request.getMethod());

        //ip
        logger.info("ip={}", request.getRemoteAddr());

        //类方法
        logger.info("class_method={}", joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());

        //参数
        logger.info("args={}", joinPoint.getArgs());
        /*显示一些参数*/
		/*SecurityContextImpl securityContextImpl = (SecurityContextImpl) request  
				 .getSession().getAttribute("SPRING_SECURITY_CONTEXT");  
				// 登录名  
				System.out.println("用户名:"  
				 + securityContextImpl.getAuthentication().getName());  
				// 登录密码，未加密的  
				System.err.println("证书:"  
				 + securityContextImpl.getAuthentication().getCredentials());  
				WebAuthenticationDetails details = (WebAuthenticationDetails) securityContextImpl  
				 .getAuthentication().getDetails();  
				// 获得访问地址  
				System.err.println("访问地址:" + details.getRemoteAddress());  
				// 获得sessionid  
				System.err.println("SessionId:" + details.getSessionId());  
				// 获得当前用户所拥有的权限  
				List<GrantedAuthority> authorities = (List<GrantedAuthority>) securityContextImpl  
				 .getAuthentication().getAuthorities();  
				for (GrantedAuthority grantedAuthority : authorities) {  
				 System.err.println("权限:" + grantedAuthority.getAuthority());  
				}*/     
    }

    @After("log()")
    public void doAfter() {
//        logger.info("业务完成后执行");
    }

    @AfterReturning(returning = "object", pointcut = "log()")
    public void doAfterReturning(Object object) {
//      logger.info("response={}", object.toString());
    }

}
