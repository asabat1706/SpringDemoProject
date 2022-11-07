package com.example.demo.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;

@Slf4j
@Aspect
@Component
public class LoggingAspect {

    @Around("execution(* com.example.demo..*.*(..))")
    public Object log(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info(joinPoint.getSignature().toString() + "method execution start");
        Instant start = Instant.now();
        Object returnObj = joinPoint.proceed();
        Instant finish = Instant.now();
        long timeTaken = Duration.between(start,finish).toMillis();
        log.info("Time taken to execute "+joinPoint.getSignature().toString() + " is "+timeTaken + " ms");
        log.info(joinPoint.getSignature().toString() + "method execution end");
        return returnObj;
    }

    @AfterThrowing(value = "execution(* com.example.demo..*.*(..))", throwing = "ex")
    public void logException(JoinPoint joinPoint, Exception ex){
        log.error(joinPoint.getSignature()+ " Exception occurred due to "+ ex.getMessage());
    }
}
