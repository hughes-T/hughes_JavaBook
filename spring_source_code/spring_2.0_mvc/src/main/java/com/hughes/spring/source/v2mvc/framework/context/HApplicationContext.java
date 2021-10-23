package com.hughes.spring.source.v2mvc.framework.context;

import com.hughes.spring.source.v2mvc.framework.annotation.GPAutowired;
import com.hughes.spring.source.v2mvc.framework.annotation.GPController;
import com.hughes.spring.source.v2mvc.framework.annotation.GPService;
import com.hughes.spring.source.v2mvc.framework.beans.HBeanWrapper;
import com.hughes.spring.source.v2mvc.framework.beans.config.HBeanDefinition;
import com.hughes.spring.source.v2mvc.framework.beans.support.HBeanDefinitionReader;
import com.hughes.spring.source.v2mvc.framework.beans.support.HDefaultListableBeanFactory;
import com.hughes.spring.source.v2mvc.framework.factory.HBeanFactory;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
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
    private final HDefaultListableBeanFactory registry ;

    private final HBeanDefinitionReader reader;




    /**
     * 三级缓存
     */
    private final Map<String,HBeanWrapper> factoryBeanInstanceCache = new HashMap<String, HBeanWrapper>();

    private final Map<String,Object> factoryBeanObjectCache = new HashMap<String, Object>();

    public HApplicationContext(String... configs) {
        registry = new HDefaultListableBeanFactory();
        //加载配置
        reader = new HBeanDefinitionReader(configs);
        try {
            //解析配置
            List<HBeanDefinition> beanDefinitions = reader.loadBeanDefinitions();
            //缓存配置
            registry.doRegistryBeanDefinition(beanDefinitions);
            //初始化非延时加载的Bean
            doInstance();
            //执行依赖注入
            populateBean();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 循环调用getBean
     */
    private void doInstance() {
        for (Map.Entry<String, HBeanDefinition> entry : registry.definitionCache.entrySet()) {
            if (!entry.getValue().isLazyInit()){
                getBean(entry.getKey());
            }
        }
    }

    @Override
    public Object getBean(Class<?> clazz) {
        return getBean(clazz.getName());
    }

    @Override
    public Object getBean(String beanName) {
        //获取元信息
        HBeanDefinition beanDefinition = registry.definitionCache.get(beanName);
        //实例化
        Object instance = instantiateBean(beanName,beanDefinition);
        //封装为wrapper
        HBeanWrapper beanWrapper = new HBeanWrapper(instance);
        //存入IOC容器(三级缓存)中
        factoryBeanInstanceCache.put(beanName,beanWrapper);
        return beanWrapper.getWrappedInstance();
    }

    private void populateBean() {
        for (Map.Entry<String, HBeanWrapper> entry : factoryBeanInstanceCache.entrySet()) {
            HBeanWrapper beanWrapper = entry.getValue();
            Object instance = beanWrapper.getWrappedInstance();
            Class<?> clazz = beanWrapper.getWrappedClass();
            if (!clazz.isAnnotationPresent(GPController.class) || clazz.isAnnotationPresent(GPService.class)){
                return;
            }
            for (Field field : clazz.getDeclaredFields()) {
                if (!field.isAnnotationPresent(GPAutowired.class)){continue;}
                GPAutowired autowired = field.getAnnotation(GPAutowired.class);
                String autowiredBeanName = autowired.value().trim();
                //没有自定义值则取接口类型
                autowiredBeanName = "".equals(autowiredBeanName) ? field.getType().getName() : autowiredBeanName;
                //暴力反射注入bean
                field.setAccessible(true);
                try {
                    field.set(instance,factoryBeanInstanceCache.get(autowiredBeanName).getWrappedInstance());
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    private Object instantiateBean(String beanName, HBeanDefinition beanDefinition) {
        String className = beanDefinition.getClassName();
        Object instance = null;
        try {
            Class<?> clazz = Class.forName(className);
            instance = clazz.newInstance();
            //代理对象,触发AOP逻辑入口

            this.factoryBeanObjectCache.put(beanName,instance);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return instance;
    }

    public int getBeanDefinitionCount(){
        return registry.definitionCache.size();
    }

    public String[] getBeanDefinitionNames(){
        return registry.definitionCache.keySet().toArray(new String[0]);
    }
}
