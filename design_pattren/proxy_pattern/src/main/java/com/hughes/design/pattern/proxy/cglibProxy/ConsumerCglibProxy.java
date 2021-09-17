package com.hughes.design.pattern.proxy.cglibProxy;

import com.hughes.design.pattern.proxy.domain.IConsumer;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * cglib代理类
 *
 * @author hughes-T
 * @since 2021/8/26 15:13
 */
public class ConsumerCglibProxy implements MethodInterceptor {

    public IConsumer getInstance(Class<? extends IConsumer> clazz) throws Exception {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(clazz);
        enhancer.setCallback(this);
        return (IConsumer) enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        before();
        Object obj = methodProxy.invokeSuper(o, objects);
        end();
        return obj;
    }

    private void before() {
        System.out.println("Cglib 开始代理");
    }

    private void end() {
        System.out.println("Cglib 结束代理");
    }
}
