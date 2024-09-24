package com.example.AuthService.security;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Around("execution(* com.example.AuthService.controller..*(..))") // Замените на ваш пакет контроллеров
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        logger.info("Entering method: {}", joinPoint.getSignature().toShortString());

        Object result;
        try {
            result = joinPoint.proceed(); // выполнение метода
        } catch (Throwable e) {
            logger.error("Exception in method: {}", joinPoint.getSignature().toShortString(), e);
            throw e;
        }

        logger.info("Exiting method: {}", joinPoint.getSignature().toShortString());
        return result;
    }
}

