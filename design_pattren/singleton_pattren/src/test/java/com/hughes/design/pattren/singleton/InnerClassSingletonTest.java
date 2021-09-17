package com.hughes.design.pattren.singleton;

import java.lang.reflect.Constructor;

/**
 * @author hughes-T
 * @since 2021/8/10 16:46
 */
public class InnerClassSingletonTest {
    public static void main(String[] args) throws Exception {
        InnerClassSingleton instance1 = InnerClassSingleton.getInstance();
        Class<InnerClassSingleton> clazz = InnerClassSingleton.class;
        Constructor<InnerClassSingleton> constructor = clazz.getDeclaredConstructor();
        constructor.setAccessible(true);//强制访问
        InnerClassSingleton instance2 = constructor.newInstance();
        System.out.println(instance1 == instance2);
    }
}
