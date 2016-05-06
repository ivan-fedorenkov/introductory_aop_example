package com.devexperts.aop;

import java.lang.annotation.*;

/**
 * Marks a method or a type that should be executed within a transaction.
 *
 * @author ifedorenkov
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Transactional {
}
