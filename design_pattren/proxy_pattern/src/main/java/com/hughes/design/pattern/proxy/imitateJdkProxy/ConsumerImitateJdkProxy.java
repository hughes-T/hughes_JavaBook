package com.hughes.design.pattern.proxy.imitateJdkProxy;

import com.hughes.design.pattern.proxy.domain.IConsumer;

import java.lang.reflect.Method;

/**
 * @author hughes-T
 * @since 2021/8/30 9:38
 */
public class ConsumerImitateJdkProxy implements ImitateJDKInvocationHandler{

    private IConsumer target;

    public IConsumer getInstance(IConsumer consumer) throws Exception {
        this.target = consumer;
        return (IConsumer) ImitateJDKProxy.newProxyInstance(new ImitateJDKClassLoader(),consumer.getClass().getInterfaces(),this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        before();
        Object result = method.invoke(this.target, args);
        end();
        return result;
    }

    private void before(){
        System.out.println("ImitateJDK 开始代理");
    }
    private void end(){
        System.out.println("ImitateJDK 结束代理");
    }

}
