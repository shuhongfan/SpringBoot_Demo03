package com.shf.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@EnableSwagger2
@Configuration
public class SwaggerConfig {
    @Bean
    public Docket docker1(){
        return new Docket(DocumentationType.SWAGGER_2).groupName("docker1");
    }

    @Bean
    public Docket docker2(){
        return new Docket(DocumentationType.SWAGGER_2).groupName("docker2");
    }

    @Bean
    public Docket docker3(){
        return new Docket(DocumentationType.SWAGGER_2).groupName("docker3");
    }

//    配置swagger的Docket的bean实例
    @Bean
    public Docket docket(Environment environment){
//        设置要显示的Docket的bean实例
        Profiles profiles = Profiles.of("dev");

//        获取项目的环境  通过environment.acceptsProfiles判断是否处在自己设定的环境当中
        boolean flag = environment.acceptsProfiles(profiles);

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("shf")  // 设置组名
                .enable(flag) // 是否启动swagger
                .select()
                // RequestHandlerSelectors: 配置要扫描接口的方式
//                basePackage: 指定要扫描的包
//                any() : 扫描全部
//                none() : 不扫描
//                withMethodAnnotation(GetMapping.class))  扫描方法上的注解
//                withClassAnnotation(RestController.class)  扫描类上的注解
                .apis(RequestHandlerSelectors.basePackage("com.shf.controller"))
                // 过滤什么路径
//              ant("/swagger/**")): 扫描swagger下的路径
//                .paths(PathSelectors.ant("/swagger/**"))
                .build();
    }

    private ApiInfo apiInfo() {
        Contact contact = new Contact("舒洪凡", "https://github.com/shuhongfan", "shuhongfan@live.com");
        return new ApiInfo(
                "舒洪凡的API文档",
                "舒洪凡的API文档",
                "V1.0",
                "https://github.com/shuhongfan",
                contact,
                "Apache 2.0",
                 "https://github.com/shuhongfan",
                new ArrayList());
    }
}
