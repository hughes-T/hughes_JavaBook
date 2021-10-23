package com.hughes.spring.source.v2mvc.framework.context;

import com.hughes.spring.source.v2mvc.framework.beans.support.HDefaultListableBeanFactory;
import com.hughes.spring.source.v2mvc.framework.factory.HBeanFactory;

import java.util.Properties;

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

    public Properties getConfig() {
        return beanFactory.getConfig();
    }
}
