package com.hughes.spring.source.v2di.framework.annotation;

import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface HAutowired {
    String value() default "";
}
