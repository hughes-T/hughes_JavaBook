package com.hughes.design.pattern.proxy.jdkProxy;

import com.hughes.design.pattern.proxy.domain.IConsumer;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author hughes-T
 * @since 2021/8/15 12:41
 */
public class ConsumerJdkProxy implements InvocationHandler {
    private IConsumer target;

    public IConsumer getInstance(IConsumer target){
        this.target = target;
        Class<?> clazz = target.getClass();
        return (IConsumer) Proxy.newProxyInstance(clazz.getClassLoader(),clazz.getInterfaces(),this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        before();
        Object result = method.invoke(this.target, args);
        after();
        return result;
    }
    private void before(){
        System.out.println("jdk开始代理");
    }
    private void after(){
        System.out.println("jdk代理结束");
    }
}
