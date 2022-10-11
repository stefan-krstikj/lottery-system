package com.stefankrstikj.lotterysystem.config;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class LoggingAspect {

    @Before("within(com.stefankrstikj.lotterysystem.controller..*))")
    public void logControllers(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        log.info("-> Reached [/{}], in class [{}]",
                signature.getMethod().getName(),
                signature.getMethod().getDeclaringClass().getName());
    }
}
