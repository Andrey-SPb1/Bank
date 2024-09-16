package com.bank.profiling;

import lombok.Getter;
import lombok.Setter;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Getter
@Setter
public class ProfilingAspect {

    private boolean flag;

    @Around("@annotation(Profiling)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object proceed = joinPoint.proceed();
        long executionTime = System.currentTimeMillis() - start;
        if(flag)System.out.printf("%s выполнен за %d мс\n", joinPoint.getSignature(), executionTime);
        return proceed;
    }
}
