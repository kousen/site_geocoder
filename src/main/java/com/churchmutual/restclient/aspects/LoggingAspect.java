package com.churchmutual.restclient.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
    private Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    //@Before("execution(* com.churchmutual.restclient.*.*.*(..))")
    public void logMethodCalls(JoinPoint joinPoint) {
        logger.info("About to execute " + joinPoint.getSignature());
    }
}
