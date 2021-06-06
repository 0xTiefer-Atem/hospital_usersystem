package org.usersystem.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Author WangQian
 * @Date 2020/9/26 下午 7:25
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    //http://localhost:9090/swagger-ui.html#/
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .pathMapping("/")
                .select()
                .apis(RequestHandlerSelectors.basePackage("org.myBlog.project.controller"))
                .paths(PathSelectors.any())
                .build().apiInfo(new ApiInfoBuilder()
                        .title("个人博客接口-Swagger")
                        .description("个人博客接口")
                        .contact(new Contact("0xTiefer-Atem", "https://github.com/0xTiefer-Atem", "1144502582@qq.com"))
                        .version("1.0")
                        .build());
    }
}
