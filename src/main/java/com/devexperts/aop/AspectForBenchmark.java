package com.devexperts.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

/**
 * @author ifedorenkov
 */
@Aspect
public class AspectForBenchmark {

    @Around(value = "within(com.devexperts.service..*) && execution(* *(..)) && @annotation(logged)")
    public Object doAround(ProceedingJoinPoint pjp, Logged logged) throws Throwable {
        return pjp.proceed();
    }

}
