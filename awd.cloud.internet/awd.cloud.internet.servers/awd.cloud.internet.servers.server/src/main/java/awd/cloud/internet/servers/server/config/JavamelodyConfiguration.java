package awd.cloud.internet.servers.server.config;

import net.bull.javamelody.*;
import org.springframework.aop.support.annotation.AnnotationMatchingPointcut;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.DispatcherType;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.io.File;

@Configuration
public class JavamelodyConfiguration implements ServletContextInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        servletContext.addListener(new SessionListener());
    }

    @Bean
    public FilterRegistrationBean javaMelody() {
        final FilterRegistrationBean javaMelody = new FilterRegistrationBean();
        final MonitoringFilter filter = new MonitoringFilter();
        filter.setApplicationType("Spring boot");

        javaMelody.setFilter(filter);
        javaMelody.setAsyncSupported(true);
        javaMelody.setName("javamelody");
        javaMelody.setDispatcherTypes(DispatcherType.REQUEST, DispatcherType.ASYNC);

        // see the list of parameters:
        // https://github.com/javamelody/javamelody/wiki/UserGuide#6-optional-parameters
        javaMelody.addInitParameter(Parameter.LOG.getCode(), Boolean.toString(true));
        // to exclude images, css, fonts and js urls from the monitoring:
        javaMelody.addInitParameter(Parameter.URL_EXCLUDE_PATTERN.getCode(), "/static/.{0,}");
        // to add basic auth:
        javaMelody.addInitParameter(Parameter.AUTHORIZED_USERS.getCode(), "njawd:dddd");
        // to change the default storage directory:
        File f = new File("./javamelody/");
        if (!f.exists()) f.mkdir();
        System.out.println("javamelody 绝对路径:" + f.getAbsolutePath() + "___" + f.getPath());
        javaMelody.addInitParameter(Parameter.STORAGE_DIRECTORY.getCode(), f.getAbsolutePath());

        //javaMelody.addInitParameter(Parameter.JPA_TRANSFORM_PATTERN.getCode(), "./javamelody");

        javaMelody.addInitParameter(Parameter.DATASOURCES.getCode(), "dataSource");

        javaMelody.addUrlPatterns("/*");
        return javaMelody;
    }

    //
//    // Note: if you have auto-proxy issues, you can add the following dependency in your pom.xml:
//    // <dependency>
//    //   <groupId>org.aspectj</groupId>
//    //   <artifactId>aspectjweaver</artifactId>
//    // </dependency>
//    @Bean
//    public DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {
//         return new DefaultAdvisorAutoProxyCreator();
//    }
//
    // monitoring of jdbc datasources:
    @Bean
    public SpringDataSourceBeanPostProcessor monitoringDataSourceBeanPostProcessor() {
        final SpringDataSourceBeanPostProcessor processor = new SpringDataSourceBeanPostProcessor();
        processor.setExcludedDatasources(null);
        return processor;
    }

    //
    // monitoring of beans or methods having @MonitoredWithSpring:
    @Bean
    public MonitoringSpringAdvisor monitoringAdvisor() {
        final MonitoringSpringAdvisor interceptor = new MonitoringSpringAdvisor();
        interceptor.setPointcut(new MonitoredWithAnnotationPointcut());
        return interceptor;
    }

    // monitoring of all services and controllers (even without having @MonitoredWithSpring):
    @Bean
    public MonitoringSpringAdvisor springServiceMonitoringAdvisor() {
        final MonitoringSpringAdvisor interceptor = new MonitoringSpringAdvisor();
        interceptor.setPointcut(new AnnotationMatchingPointcut(org.springframework.stereotype.Service.class));
        return interceptor;
    }

    @Bean
    public MonitoringSpringAdvisor springControllerMonitoringAdvisor() {
        final MonitoringSpringAdvisor interceptor = new MonitoringSpringAdvisor();
        interceptor.setPointcut(new AnnotationMatchingPointcut(org.springframework.stereotype.Controller.class));
        return interceptor;
    }


    @Bean
    public MonitoringSpringAdvisor springRestControllerMonitoringAdvisor() {
        final MonitoringSpringAdvisor interceptor = new MonitoringSpringAdvisor();
        interceptor.setPointcut(new AnnotationMatchingPointcut(RestController.class));
        return interceptor;
    } 
}