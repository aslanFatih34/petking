package com.fatih.petking.infrastructure.interceptor.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class TransactionTimeMeasurementAspect {

    @Pointcut("within(@org.springframework.transaction.annotation.Transactional *))")
    public void classPointcut() {
        // Method is empty as this is just a Pointcut, the implementations are in the advices.
    }

    @Pointcut("within(@org.springframework.stereotype.Repository *))")
    public void repositoryPointcut() {
        // Method is empty as this is just a Pointcut, the implementations are in the advices.
    }

    @Pointcut("@annotation(org.springframework.transaction.annotation.Transactional))")
    public void methodPointcut() {
        // Method is empty as this is just a Pointcut, the implementations are in the advices.
    }

    @Around("methodPointcut() || classPointcut() || repositoryPointcut()")
    public Object methodMeasureExecutionTime(final ProceedingJoinPoint pjp) throws Throwable {
        String className = pjp.getTarget().getClass().getSimpleName();
        String methodName = pjp.getSignature().getName();
        long startTime = System.currentTimeMillis();
        log.info("Transactional method {}.{} started.", className, methodName);
        try {
            return pjp.proceed();
        } finally {
            log.info("Transactional method {}.{} completed, takes {} ms.", className, methodName, (System.currentTimeMillis() - startTime));
        }
    }
}
