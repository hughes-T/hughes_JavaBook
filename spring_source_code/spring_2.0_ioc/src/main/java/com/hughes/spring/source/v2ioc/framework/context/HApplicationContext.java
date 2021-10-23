package com.hughes.spring.source.v2ioc.framework.context;

import com.hughes.spring.source.v2ioc.framework.annotation.HAutowired;
import com.hughes.spring.source.v2ioc.framework.annotation.HController;
import com.hughes.spring.source.v2ioc.framework.annotation.HService;
import com.hughes.spring.source.v2ioc.framework.beans.HBeanWrapper;
import com.hughes.spring.source.v2ioc.framework.beans.config.HBeanDefinition;
import com.hughes.spring.source.v2ioc.framework.beans.support.HDefaultListableBeanFactory;
import com.hughes.spring.source.v2ioc.framework.factory.HBeanFactory;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * IOC 上下文, 门面模式,负责提供给用户访问
 *
 * @author hughes-T
 * @since 2021/10/6 22:49
 */
public class HApplicationContext implements HBeanFactory {

    /**
     * 持有 BeanFactory 的引用
     */
    private final HDefaultListableBeanFactory beanFactory;

    public HApplicationContext(String... configs) {
        beanFactory = new HDefaultListableBeanFactory(configs);
    }


    @Override
    public Object getBean(Class<?> clazz) {
        return getBean(clazz.getName());
    }

    @Override
    public Object getBean(String beanName) {
        return beanFactory.getBean(beanName);
    }

    public int getBeanDefinitionCount(){
        return beanFactory.definitionCache.size();
    }

    public String[] getBeanDefinitionNames(){
        return beanFactory.definitionCache.keySet().toArray(new String[0]);
    }
}
