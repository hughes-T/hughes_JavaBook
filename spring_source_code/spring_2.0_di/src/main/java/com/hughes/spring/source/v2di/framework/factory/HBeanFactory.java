package com.hughes.spring.source.v2di.framework.factory;

/**
 * IOC容器顶层接口
 *
 * @author hughes-T
 * @since 2021/10/6 22:46
 */
public interface HBeanFactory {

    Object getBean(Class<?> clazz);

    Object getBean(String beanName);

}
