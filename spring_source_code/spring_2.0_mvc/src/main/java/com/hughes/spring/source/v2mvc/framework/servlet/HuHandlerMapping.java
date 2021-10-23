package com.hughes.spring.source.v2mvc.framework.servlet;

import java.lang.reflect.Method;
import java.util.regex.Pattern;

/**
 * @author hughes-T
 * @since 2021/10/23 19:35
 */
public class HuHandlerMapping {

    private Object controller;
    protected Method method;
    protected Pattern pattern;

    public HuHandlerMapping(Object controller, Method method, Pattern pattern) {
        this.controller = controller;
        this.method = method;
        this.pattern = pattern;
    }

    public Object getController() {
        return controller;
    }

    public Method getMethod() {
        return method;
    }

    public Pattern getPattern() {
        return pattern;
    }
}
