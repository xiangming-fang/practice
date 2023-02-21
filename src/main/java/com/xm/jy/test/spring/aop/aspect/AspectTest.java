package com.xm.jy.test.spring.aop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @auther 方翔鸣
 * @date 2020/3/9 10:03
 */
@Aspect
@Component
public class AspectTest {

    Logger logger = LoggerFactory.getLogger(AspectTest.class);

//    @Pointcut("execution ( public * com.cx.timer.aop.controller..*.*(..))")
    @Pointcut("execution ( public * com.xm.jy.test.spring.aop.controller.SayHelloController.*(..))")
    public void ponint(){};

    @Before("ponint()")
    public void before(JoinPoint joinPoint){
        // 接收到请求，并记录请求内容
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        // 记录请求内容
        logger.info("URL:"+request.getRequestURL().toString());
        logger.info("RemoteIP:"+request.getRemoteAddr());
        logger.info("LocalIP:"+request.getLocalAddr());
    }

    @AfterReturning(returning = "ret",pointcut = "ponint()")
    public void afterReturning(Object ret){
        // 处理完相应请求的返回内容
        logger.info("response:"+ret);
    }
}
