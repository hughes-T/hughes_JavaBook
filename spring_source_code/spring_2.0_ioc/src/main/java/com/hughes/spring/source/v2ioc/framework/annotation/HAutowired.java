package com.hughes.spring.source.v2ioc.framework.annotation;

import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface HAutowired {
    String value() default "";
}
