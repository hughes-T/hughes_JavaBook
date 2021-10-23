package com.hughes.spring.source.v2di.framework.beans.support;

import com.hughes.spring.source.v2di.framework.annotation.HAutowired;
import com.hughes.spring.source.v2di.framework.annotation.HController;
import com.hughes.spring.source.v2di.framework.annotation.HService;
import com.hughes.spring.source.v2di.framework.beans.HBeanWrapper;
import com.hughes.spring.source.v2di.framework.beans.config.HBeanDefinition;
import com.hughes.spring.source.v2di.framework.factory.HBeanFactory;

import java.lang.reflect.Field;
import java.util.*;

/**
 * @author hughes-T
 * @since 2021/10/6 22:51
 */
public class HDefaultListableBeanFactory implements HBeanFactory {

    /**
     * 配置缓存
     */
    public final Map<String,HBeanDefinition> definitionCache = new HashMap<>();

    /**
     *  循环依赖的标识，当前正在创建的BeanName，Mark一下
     */
    private Set<String> singletonsCurrentlyInCreation = new HashSet<>();

    //一级缓存：保存成熟的Bean
    private Map<String,Object> singletonObjects = new HashMap<>();

    //二级缓存：保存早期的Bean
    private Map<String,Object> earlySingletonObjects = new HashMap<>();

    //三级缓存（终极缓存）
    private Map<String,HBeanWrapper> factoryBeanInstanceCache = new HashMap<>();
    private Map<String,Object> factoryBeanObjectCache = new HashMap<>();

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

        //enter
        Object singleton = getSingleton(beanName,beanDefinition);
        if (singleton != null){return singleton;}

        //不存在标记bean创建中
        if (!singletonsCurrentlyInCreation.contains(beanName)){
            singletonsCurrentlyInCreation.add(beanName);
        }

        //实例化
        Object instance = instantiateBean(beanName,beanDefinition);

        //封装为wrapper
        HBeanWrapper beanWrapper = new HBeanWrapper(instance);

        //依赖注入
        populateBean(beanWrapper);

        //放入一级缓存
        this.singletonObjects.put(beanName,instance);

        //存入IOC容器(三级缓存)中
        factoryBeanInstanceCache.put(beanName,beanWrapper);
        return beanWrapper.getWrappedInstance();
    }

    private Object getSingleton(String beanName, HBeanDefinition beanDefinition) {
        //一级缓存获取
        Object bean = singletonObjects.get(beanName);
        //一级缓存没有且有创建标记,说明是循环依赖
        if(bean == null && singletonsCurrentlyInCreation.contains(beanName)){
            bean = earlySingletonObjects.get(beanName);
            //如果二级缓存也没有，那就从三级缓存中拿
            if(bean == null){
                bean = instantiateBean(beanName,beanDefinition);
                //将创建出来的对象重新放入到二级缓存中
                earlySingletonObjects.put(beanName,bean);
            }
        }

        return bean;
    }

    private void populateBean(HBeanWrapper beanWrapper) {
        Object instance = beanWrapper.getWrappedInstance();

        Class<?> clazz = beanWrapper.getWrappedClass();

        if(!(clazz.isAnnotationPresent(HController.class) || clazz.isAnnotationPresent(HService.class))){
            return;
        }

        //忽略字段的修饰符，不管你是 private / protected / public / default
        for (Field field : clazz.getDeclaredFields()) {
            if(!field.isAnnotationPresent(HAutowired.class)){ continue; }

            HAutowired autowired = field.getAnnotation(HAutowired.class);
            String autowiredBeanName = autowired.value().trim();
            if("".equals(autowiredBeanName)){
                autowiredBeanName = toLowerFirstCase( field.getType().getSimpleName());
            }

            //代码在反射面前，那就是裸奔
            //强制访问，强吻
            field.setAccessible(true);

            try {
//                if(this.factoryBeanInstanceCache.get(autowiredBeanName) == null){
//                    continue;
//                }

                //相当于 demoAction.demoService = ioc.get("com.gupaoedu.demo.service.IDemoService");
//                field.set(instance,this.factoryBeanInstanceCache.get(autowiredBeanName).getWrappedInstance());
                field.set(instance,getBean(autowiredBeanName));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
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
        if(beanDefinition.isSingleton() && this.factoryBeanObjectCache.containsKey(beanName)){
            return this.factoryBeanObjectCache.get(beanName);
        }

        String className = beanDefinition.getClassName();
        Object instance = null;
        try {
            Class<?> clazz = Class.forName(className);
            instance = clazz.newInstance();
            //代理对象,触发AOP逻辑入口

            this.factoryBeanObjectCache.put(beanName,instance);
            this.factoryBeanObjectCache.put(clazz.getName(),instance);
            for (Class<?> i : clazz.getInterfaces()) {
                this.factoryBeanObjectCache.put(i.getName(),instance);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return instance;
    }

}
