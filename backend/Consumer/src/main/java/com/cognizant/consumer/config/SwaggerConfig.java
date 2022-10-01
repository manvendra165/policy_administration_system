package com.cognizant.consumer.config;

import java.util.Collections;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.*;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	public Docket swaggerConfiguration() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.paths(PathSelectors.ant("/**"))
			.apis(RequestHandlerSelectors.basePackage("com.cognizant.consumer"))
				.build().apiInfo(apiDetails());
	}

	private ApiInfo apiDetails() {
		return new ApiInfo("Consumer Business API Documentation",
				"Consumer Business API for swagger",
				"1.0", "Free to use",
				new springfox.documentation
				.service.Contact("Easy Policy", "www.consumer.com",
						"easypolicy@cognizant.com"),
				"API License", "consumer.com", Collections.emptyList());

	}
}
