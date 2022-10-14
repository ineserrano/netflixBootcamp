package com.nttdata.di4.netflixfilms.config;

import com.nttdata.di4.netflixfilms.utils.RestConstants;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select().paths(regex(".*" + RestConstants.APPLICATION_NAME + RestConstants.API_VERSION_1 + "/.*")).build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("Netflix").description("Films Api").build();
    }
}
