package com.aop.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {
	@Bean
	public Docket api() {

		return new Docket(DocumentationType.SWAGGER_2)//
				.apiInfo(getApiInfo())//
				.select()//
				.apis(RequestHandlerSelectors.any())//
				.paths(PathSelectors.ant("/api/**"))//
				.build();
	}

	private ApiInfo getApiInfo() {
		ApiInfoBuilder builder = new ApiInfoBuilder();
		builder.title("AOP Example API");
		builder.description("An Example Rest API for AOP Implementation");
		builder.version("1.0");
		return builder.build();
	}
}
