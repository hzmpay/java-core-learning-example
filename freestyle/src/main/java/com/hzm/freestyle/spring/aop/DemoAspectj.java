package com.hzm.freestyle.spring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

/**
 * @author Hezeming
 * @version 1.0
 * @date 2021年04月27日
 */
@Aspect
@EnableAspectJAutoProxy(exposeProxy = true, proxyTargetClass = false)
@Component
public class DemoAspectj {

    @Pointcut("execution(public * com.hzm.freestyle.spring.aop..*.*(..))")
    public void pointCut() {}


    @Around("pointCut()")
    public void before(ProceedingJoinPoint joinPoint) throws Throwable {
        joinPoint.getTarget();

        System.out.println("before .........");
        joinPoint.proceed();
        System.out.println("after .........");

    }

}
