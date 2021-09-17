package com.hughes.design.pattern.proxy.imitateJdkProxy;

import java.lang.reflect.Method;

/**
 * @author hughes-T
 * @since 2021/8/26 15:42
 */
public interface ImitateJDKInvocationHandler {

    Object invoke(Object proxy, Method method, Object[] args) throws Throwable;

}
