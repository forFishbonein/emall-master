package com.emall.common.aop;

import java.lang.annotation.*;

/**
 * 日志注解
 */
//这是三个元注解：
//Type代表可以放在类上面，Method代表可以放在方法上面
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LogAnnotation {

    String module() default ""; //模块名称

    String operator() default ""; //操作名称
}