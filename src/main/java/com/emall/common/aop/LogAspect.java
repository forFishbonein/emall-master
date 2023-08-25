package com.emall.common.aop;

import com.alibaba.fastjson.JSON;
import com.emall.utils.HttpContextUtils;
import com.emall.utils.IpUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * 日志切面
 *
 * 日志的好处：
 * 1.在用户进行浏览等操作时都会在后台产生日志记录
 * 2.当应用程序发生错误时，可以快速定位错误位置
 * 3.可以看到相关操作的时间性能等信息
 */
@Aspect //切面，定义了通知和切点的关系
@Component
@Slf4j //记录日志
public class LogAspect {


    @Pointcut("@annotation(com.emall.common.aop.LogAnnotation)") //定义一个切点
    public void logPointCut() {
    }

    @Around("logPointCut()") //环绕通知：即日志
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        long beginTime = System.currentTimeMillis();
        //执行方法
        Object result = joinPoint.proceed();
        //执行时长(毫秒)
        long time = System.currentTimeMillis() - beginTime;
        //保存日志
        recordLog(joinPoint, time);
        return result;
    }

    private void recordLog(ProceedingJoinPoint joinPoint, long time) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        LogAnnotation logAnnotation = method.getAnnotation(LogAnnotation.class);
        log.info("=====================log start================================");
        log.info("module:{}",logAnnotation.module()); //得到模块名
        log.info("operation:{}",logAnnotation.operator()); //得到操作名

        //请求的方法名
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();
        log.info("request method:{}",className + "." + methodName + "()");

//        //请求的参数
        Object[] args = joinPoint.getArgs();
        String params = JSON.toJSONString(args[0]);
        log.info("params:{}",params);

        //获取request 设置IP地址
        HttpServletRequest request = HttpContextUtils.getHttpServletRequest(); //工具类：获取request
        log.info("ip:{}", IpUtils.getIpAddr(request)); //工具类：获取ip地址


        log.info("excute time : {} ms",time);
        log.info("=====================log end================================");
    }

}
