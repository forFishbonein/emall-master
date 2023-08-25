package com.emall;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;

//在启动springboot时会自动实例化一个mongo实例，需要禁用自动配置，否则报错
@SpringBootApplication(exclude = MongoAutoConfiguration.class)
//开启缓存功能注解@EnableCaching
@EnableCaching
@Slf4j
public class EmallBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmallBackendApplication.class, args);
        log.info("----------------------success----------------------");
    }

}
