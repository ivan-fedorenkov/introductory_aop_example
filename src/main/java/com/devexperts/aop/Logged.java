package com.devexperts.aop;

import java.lang.annotation.*;

/**
 * Marks a method that should be logged according to our business needs.
 *
 * @author ifedorenkov
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Logged {

    /**
     * Determines the appropriate log level.
     * Default level is {@link LogLevel#DEBUG}.
     */
    LogLevel level() default LogLevel.DEBUG;

}
