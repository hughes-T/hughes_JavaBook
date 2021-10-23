package com.hughes.spring.source.v2ioc.framework.beans.support;

import com.hughes.spring.source.v2ioc.framework.beans.config.HBeanDefinition;
import com.hughes.spring.source.v2ioc.framework.core.HBeanFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author hughes-T
 * @since 2021/10/6 22:51
 */
public class HDefaultListableBeanFactory implements HBeanFactory {

    public final Map<String,HBeanDefinition> definitionCache = new HashMap<String, HBeanDefinition>();

    @Override
    public Object getBean(Class<?> clazz) {
        return null;
    }

    @Override
    public Object getBean(String beanName) {
        return null;
    }

    public void doRegistryBeanDefinition(List<HBeanDefinition> beanDefinitions) throws Exception {
        for (HBeanDefinition beanDefinition : beanDefinitions) {
            if (definitionCache.containsKey(beanDefinition.getBeanName())){
                throw new Exception("The" + beanDefinition.getBeanName() + "is exists!!");
            }
            definitionCache.put(beanDefinition.getBeanName(), beanDefinition);
        }
    }

}
