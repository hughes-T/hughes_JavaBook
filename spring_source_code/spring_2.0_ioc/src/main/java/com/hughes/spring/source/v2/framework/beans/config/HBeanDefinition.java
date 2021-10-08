package com.hughes.spring.source.v2.framework.beans.config;

/**
 * @author hughes-T
 * @since 2021/10/6 23:09
 */
public class HBeanDefinition {

    public boolean isLazyInit(){
        return false;
    }

    private String beanName;

    private String className;

    public HBeanDefinition(String beanName, String className) {
        this.beanName = beanName;
        this.className = className;
    }

    public String getBeanName() {
        return beanName;
    }

    public String getClassName() {
        return className;
    }
}
