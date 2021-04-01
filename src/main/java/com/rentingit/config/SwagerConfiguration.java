package com.rentingit.config;


import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.models.parameters.Parameter;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwagerConfiguration {
	
	springfox.documentation.service.Parameter authHeader = new ParameterBuilder()
			  .parameterType("header")
			  .name("Authorization")
			  .modelRef(new ModelRef("string"))
			  .build();

	@Bean
    public Docket redditCloneApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .apiInfo(getApiInfo())
        		.globalOperationParameters(Collections.singletonList(authHeader));
    }

	
	
    private ApiInfo getApiInfo() {
        return new ApiInfoBuilder()
                .title("RentingIt Application API")
                .version("1.0")
                .description("API for RentingIt Web App")
                .contact(new Contact("mindfiresolutions", "http://www.mindfiresolutions.com", "ujjwalk@mindfiresolutions.com"))
                .license("Apache License Version 2.0")
                .build();
    }
}