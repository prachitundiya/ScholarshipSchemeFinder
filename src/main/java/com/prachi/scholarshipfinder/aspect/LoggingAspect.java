package com.prachi.scholarshipfinder.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.AfterReturning;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    @Before("execution(* com.prachi.scholarshipfinder.serviceimpl.*.*(..))")
    public void beforeMethod(JoinPoint joinPoint) {

        System.out.println("\n==================================");
        System.out.println("AOP LOG");
        System.out.println("Starting Method : " + joinPoint.getSignature().getName());
        System.out.println("==================================");
    }

    @AfterReturning("execution(* com.prachi.scholarshipfinder.serviceimpl.*.*(..))")
    public void afterMethod(JoinPoint joinPoint) {

        System.out.println("Completed Method : " + joinPoint.getSignature().getName());
        System.out.println("==================================\n");
    }
}