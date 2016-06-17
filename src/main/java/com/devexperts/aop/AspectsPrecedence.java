package com.devexperts.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclarePrecedence;

/**
 * @author ifedorenkov
 */
@Aspect
@DeclarePrecedence("com.devexperts.aop.TransactionalAspect,com.devexperts.aop.LoggedAspect")
public class AspectsPrecedence {
}
