package com.hughes.spring.source.v2.framework.beans;

/**
 * Bean 包装器
 * @author hughes-T
 * @since 2021/10/7 17:29
 */
public class HBeanWrapper {

    private Object wrappedInstance;

    private Class<?> wrappedClass;

    public HBeanWrapper(Object instance) {
        this.wrappedInstance = instance;
        this.wrappedClass = instance.getClass();
    }

    public Object getWrappedInstance() {
        return wrappedInstance;
    }

    public Class<?> getWrappedClass() {
        return wrappedClass;
    }
}
