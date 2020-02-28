package awd.cloud.internet.web.manager.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import awd.cloud.internet.web.manager.filter.WebHandlerInterceptor;


@Configuration
public class MyWebConfig extends WebMvcConfigurerAdapter{
	
	@Bean   //把我们的拦截器注入为bean
    public HandlerInterceptor getMyInterceptor(){
        return new WebHandlerInterceptor();
    }

    /**
     * 注册 拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
    	registry.addInterceptor(getMyInterceptor()).addPathPatterns("/**").excludePathPatterns("/logout");
        super.addInterceptors(registry);
    }

}
