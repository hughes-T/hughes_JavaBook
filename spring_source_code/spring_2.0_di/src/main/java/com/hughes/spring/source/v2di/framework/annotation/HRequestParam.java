package com.hughes.spring.source.v2di.framework.annotation;

import java.lang.annotation.*;

@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface HRequestParam {
    String value() default "";
}
