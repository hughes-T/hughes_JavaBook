package com.hughes.spring.source.v2di.framework.annotation;

import java.lang.annotation.*;

@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface HRequestMapping {
    String value() default "";
}
