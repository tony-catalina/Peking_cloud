package awd.cloud.internet.servers.server.config;

import awd.framework.common.utils.OpenAPI;
import com.google.common.base.Predicate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.async.DeferredResult;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger2的配置类
 *
 */

@Configuration
@EnableSwagger2
public class Swagger2 {

	@Value("${swagger2.package}")
    private String basePackage;
	
	@Value("${swagger2.show.dev}")
	private String dev;
	
	@Value("${swagger2.show.pro}")
	private String pro;
	
	@Value("${spring.application.name}")
    private String title;

    /**
     * 定义api组，
     */
    @Bean
    @ConditionalOnExpression("${swagger2.show.dev:true}")
    public Docket innerApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("innerApi")
                .genericModelSubstitutes(DeferredResult.class)
                .useDefaultResponseMessages(false)
                .forCodeGeneration(true)
                .select()
                .apis(RequestHandlerSelectors.basePackage(basePackage))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(innerApiInfo());
    }

    @Bean
    @ConditionalOnExpression("${swagger2.show.pro:true}")
    public Docket openApi() {
        Predicate<RequestHandler> predicate = new Predicate<RequestHandler>() {
            @Override
            public boolean apply(RequestHandler input) {
                if (input.isAnnotatedWith(OpenAPI.class))//只有添加了ApiOperation注解的method才在API中显示
                    return true;
                return false;
            }
        };
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("openApi")
                .genericModelSubstitutes(DeferredResult.class)
                .useDefaultResponseMessages(false)
                .forCodeGeneration(false)
                .select()
                .apis(predicate)
                .paths(PathSelectors.any())//过滤的接口
                .build()
                .apiInfo(openApiInfo());
    }

    private ApiInfo innerApiInfo() {
        return new ApiInfoBuilder()
                .title(title)//大标题
                .description("监管云平台内部api文档")//详细描述
                .termsOfServiceUrl("http://www.njawd.com/")
                .license("MIT")
                .version("1.0")
                .build();
    }

    private ApiInfo openApiInfo() {
        return new ApiInfoBuilder()
                .title(title)//大标题
                .description("监管云平台对外api文档")//详细描述
                .termsOfServiceUrl("http://www.njawd.com/")
                .license("MIT")
                .version("1.0")
                .build();
    }


}
