package awd;


import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.beetl.core.resource.ClasspathResourceLoader;
import org.beetl.ext.spring.BeetlGroupUtilConfiguration;
import org.beetl.ext.spring.BeetlSpringViewResolver;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.feign.support.SpringEncoder;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternUtils;

import awd.cloud.internet.app.smsy.api.AwdApi;
import awd.cloud.internet.app.smsy.controller.SmsyPhaseChangeController;
import awd.cloud.internet.app.smsy.model.UnitModel;
import awd.cloud.internet.app.smsy.utils.KssDictionay;
import awd.cloud.internet.app.smsy.utils.PagerResult;
import awd.cloud.internet.app.smsy.utils.QueryParam;
import awd.cloud.internet.app.smsy.utils.ResponseMessage;

@SpringBootApplication
@EnableDiscoveryClient
@EnableEurekaClient
@Configuration
@EnableHystrix
@EnableFeignClients
public class SmsyWebApplication implements CommandLineRunner{
	  @Autowired
	  private AwdApi awdApi;
	
    public static void main( String[] args ){
        SpringApplication.run(SmsyWebApplication.class, args);
    }

    @Autowired
    void setEnviroment(Environment env) {
        System.out.println("my-config.appName from env: " + env.getProperty("spring.application.name"));
    }
    @Bean
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager("entities");
    }

    @Bean(initMethod = "init", name = "beetlConfig")
    public BeetlGroupUtilConfiguration getBeetlGroupUtilConfiguration() {
        BeetlGroupUtilConfiguration beetlGroupUtilConfiguration = new BeetlGroupUtilConfiguration();
        ResourcePatternResolver patternResolver = ResourcePatternUtils.getResourcePatternResolver(new DefaultResourceLoader());
        try {
            ClasspathResourceLoader resourceLoader = new ClasspathResourceLoader(
                    this.getClass().getClassLoader(), "/templates");
            beetlGroupUtilConfiguration.setResourceLoader(resourceLoader);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //读取配置文件信息
        return beetlGroupUtilConfiguration;
    }
    @Bean(name = "beetlViewResolver")
    public BeetlSpringViewResolver getBeetlSpringViewResolver() {
        BeetlSpringViewResolver beetlSpringViewResolver = new BeetlSpringViewResolver();
        beetlSpringViewResolver.setPrefix("/");
        beetlSpringViewResolver.setSuffix(".html");
        beetlSpringViewResolver.setContentType("text/html;charset=UTF-8");
        beetlSpringViewResolver.setOrder(0);
        beetlSpringViewResolver.setConfig(getBeetlGroupUtilConfiguration());
        return beetlSpringViewResolver;
    }

    @Override
    public void run(String... args) throws Exception {
    	System.out.println(">>>>>>>>>>>>>>上门收押前台应用系统启动<<<<<<<<<<<<<");

        System.out.println(">>>>>>>>>>>>>>上门收押前台应用系统启动结束<<<<<<<<<<<<<");
    }
}
