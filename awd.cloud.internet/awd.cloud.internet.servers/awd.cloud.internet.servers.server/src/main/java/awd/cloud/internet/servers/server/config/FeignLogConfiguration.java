package awd.cloud.internet.servers.server.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @program: awd.swj
 * @description: feign日志设置
 * @author: WS
 * @create: 2018-11-27
 **/

@Configuration
public class FeignLogConfiguration {
    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

}
