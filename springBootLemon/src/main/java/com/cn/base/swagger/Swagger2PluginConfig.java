/**
 * Copyright (c) 2018,http://www.365wuliu.com/  All Rights Reserved.
 */
package com.cn.base.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
/** 
 * CustomJavaPluginConfig:api插件配置
 * @Project Name:springBootLemon 
 * @File Name:CustomJavaPluginConfig.java 
 * @Package Name:com.cn.common.swagger
 * @Creator:LU YONG JUN
 * @Date:2018年1月26日下午2:28:05 
 */
@Configuration
@EnableSwagger2
public class Swagger2PluginConfig extends WebMvcConfigurerAdapter{
	
	@Bean
    public Docket useApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .paths(path -> path.startsWith("/api/"))
                .build()
                .apiInfo(testApiInfo());
    }
	
	private ApiInfo testApiInfo() {
        return new ApiInfoBuilder()
            .title("Electronic Health Record(EHR) Platform API")//大标题
            .description("EHR Platform's REST API, all the applications could access the Object model data via JSON.")//详细描述
            .version("1.0")//版本
            .termsOfServiceUrl("NO terms of service")
            .contact(new Contact("小单", "http://blog.csdn.net/catoop", "365384722@qq.com"))//作者
            .license("The Apache License, Version 2.0")
            .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
            .build();
    }
}
