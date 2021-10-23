package com.hughes.spring.source.v2ioc.framework.beans.support;

import com.hughes.spring.source.v2ioc.framework.annotation.HAutowired;
import com.hughes.spring.source.v2ioc.framework.annotation.HController;
import com.hughes.spring.source.v2ioc.framework.annotation.HService;
import com.hughes.spring.source.v2ioc.framework.beans.HBeanWrapper;
import com.hughes.spring.source.v2ioc.framework.beans.config.HBeanDefinition;
import com.hughes.spring.source.v2ioc.framework.factory.HBeanFactory;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author hughes-T
 * @since 2021/10/6 22:51
 */
public class HDefaultListableBeanFactory implements HBeanFactory {

    /**
     * 配置缓存
     */
    public final Map<String,HBeanDefinition> definitionCache = new HashMap<String, HBeanDefinition>();

    /**
     * 最终缓存
     */
    private final Map<String,HBeanWrapper> factoryBeanInstanceCache = new HashMap<String, HBeanWrapper>();

    /**
     * 纯净bean缓存
     */
    private final Map<String,Object> factoryBeanObjectCache = new HashMap<String, Object>();

    private final HBeanDefinitionReader reader;

    public HDefaultListableBeanFactory(String... configs) {
        //加载配置
        reader = new HBeanDefinitionReader(configs);

        try {
            //解析配置
            List<HBeanDefinition> beanDefinitions = reader.loadBeanDefinitions();
            //缓存配置
            doRegistryBeanDefinition(beanDefinitions);
            //初始化非延时加载的Bean
            doInstance();
            //执行依赖注入
            populateBean();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void doRegistryBeanDefinition(List<HBeanDefinition> beanDefinitions) throws Exception {
        for (HBeanDefinition beanDefinition : beanDefinitions) {
            if (definitionCache.containsKey(beanDefinition.getBeanName())){
                throw new Exception("The" + beanDefinition.getBeanName() + "is exists!!");
            }
            definitionCache.put(beanDefinition.getBeanName(), beanDefinition);
        }
    }

    /**
     * 循环调用getBean
     */
    private void doInstance() {
        for (Map.Entry<String, HBeanDefinition> entry : definitionCache.entrySet()) {
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
        HBeanDefinition beanDefinition = definitionCache.get(beanName);
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
            if (!clazz.isAnnotationPresent(HController.class) || clazz.isAnnotationPresent(HService.class)){
                return;
            }
            for (Field field : clazz.getDeclaredFields()) {
                if (!field.isAnnotationPresent(HAutowired.class)){continue;}
                HAutowired autowired = field.getAnnotation(HAutowired.class);
                String autowiredBeanName = autowired.value().trim();
                //没有自定义值则取接口类型
                autowiredBeanName = "".equals(autowiredBeanName) ? toLowerFirstCase(field.getType().getSimpleName())  : autowiredBeanName;
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
    private String toLowerFirstCase(String simpleName) {
        char [] chars = simpleName.toCharArray();
        //大小写字母的ASCII码相差32，
        chars[0] += 32;
        return String.valueOf(chars);
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

}
