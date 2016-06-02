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
        try {
            Thread.sleep(1);
        } catch (InterruptedException ie) {
            throw new RuntimeException(ie);
        }
        Object result =  pjp.proceed();
        try {
            Thread.sleep(1);
        } catch (InterruptedException ie) {
            throw new RuntimeException(ie);
        }
        return result;
    }

}
