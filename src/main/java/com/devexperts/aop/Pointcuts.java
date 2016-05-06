package com.devexperts.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * @author ifedorenkov
 */
@Aspect
public class Pointcuts {

    @Pointcut("within(com.devexperts.service..*)")
    public void withinServicePackage() {}

}
