/*
 * @Time : 2022/9/6 16:36
 * @Author : hao
 * @File : SwaggerConfig.java
 * @Software : IntelliJ IDEA
 */
package com.emall.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration //配置类
@EnableSwagger2 //开启Swagger2(的自动配置)
public class SwaggerConfig {

    @Bean
    public Docket docket() {

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .enable(true)
                .select() // 通过.select()方法，去配置扫描接口（即controller类），用RequestHandlerSelectors类配置扫描方法
                .apis(RequestHandlerSelectors.basePackage("com.emall.controller"))
                .build();
    }

    //配置文档信息
    private ApiInfo apiInfo() {

        Contact contact = new Contact("emall团队", "http://fishbones.com.cn", "1558637209@qq.com");
        return new ApiInfo(
                "emall", // 标题
                "电商平台练习", // 描述
                "v1.0", // 版本
                "http://terms.service.url/组织链接", // 组织链接
                contact, // 联系人信息
                "Apach 2.0 许可", // 许可
                "许可链接", // 许可连接
                new ArrayList<>()// 扩展
        );
    }
}
