package com.devexperts.aop;

import java.lang.annotation.*;

/**
 * @author ifedorenkov
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Transactional {
}
