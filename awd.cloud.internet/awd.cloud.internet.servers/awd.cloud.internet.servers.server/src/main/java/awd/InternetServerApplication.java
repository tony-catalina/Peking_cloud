package awd;

import awd.cloud.internet.servers.server.config.CacheConfig;
import awd.framework.common.dao.api.datasource.DataSourceHolder;
import awd.framework.common.dao.api.datasource.DatabaseType;
import awd.framework.common.datasource.orm.rdb.executor.AbstractJdbcSqlExecutor;
import awd.framework.common.datasource.orm.rdb.executor.SqlExecutor;
import awd.framework.expands.logging.AccessLoggerListener;
import com.alibaba.fastjson.JSON;
import net.bull.javamelody.MonitoredWithSpring;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.sql.DataSource;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.stream.Stream;

@SpringBootApplication
@EnableAspectJAutoProxy(exposeProxy=true)
@EnableDiscoveryClient
@MonitoredWithSpring
@EnableCaching//启用缓存
public class InternetServerApplication implements CommandLineRunner{

	@Autowired
	private CacheConfig cacheConfig;

    public static void main(String[] args) {
    	SpringApplication.run(InternetServerApplication.class,args);
	}

    @Autowired
    void setEnviroment(Environment env) {
        System.out.println("spring.application.name from env: "+ env.getProperty("spring.application.name"));
    }
    @Bean
    public CacheManager cacheManager() {
    	cacheConfig.getCacheManager();
    	cacheConfig.getUserCacheManager();
        return new ConcurrentMapCacheManager("entities");
    }

	@Bean
    public AccessLoggerListener accessLoggerListener() {
        @SuppressWarnings("rawtypes")
		Class excludes[] = {
                ServletRequest.class,
                ServletResponse.class,
                InputStream.class,
                OutputStream.class,
                MultipartFile.class
        };
        return loggerInfo -> System.out.println("有请求啦:" + JSON.toJSONString(loggerInfo.toSimpleMap(obj -> {
            if (Stream.of(excludes).anyMatch(aClass -> aClass.isInstance(obj))) return obj.getClass().getName();
            return JSON.toJSONString(obj);
        })));
    }

    @Bean
    @ConditionalOnMissingBean(SqlExecutor.class)
    public SqlExecutor sqlExecutor(DataSource dataSource) {    
        DataSourceHolder.install(dataSource, DatabaseType.mysql);
        return new AbstractJdbcSqlExecutor() {
            @Override
            public Connection getConnection() {
                return DataSourceUtils.getConnection(dataSource);
            }

            @Override
            public void releaseConnection(Connection connection) throws SQLException {
                DataSourceUtils.releaseConnection(connection, dataSource);
            }
        };

    }
    
	@Override
	public void run(String... arg0) throws Exception {
		System.err.println("准备数据缓存--------------");
		//cacheConfig.setUnitCache();	
		//cacheConfig.setUserCache();
		cacheConfig.setOrgCache();
		System.err.println("数据缓存结束--------------");
		System.out.println(">>>>>>>>>>>>>>>Internet服务启动完成，执行加载数据等操作<<<<<<<<<<<<<");
	}
    
}
