package com.hughes.config;

/**
 * @author hughes-T
 * @since 2021/12/8 15:19
 */
public class ClassLoaderTest {
    public static void main(String[] args) {
        ClassLoader classLoader = ClassLoaderTest.class.getClassLoader();
        System.out.println("classLoader is:" + classLoader.toString());
//        classLoader = int.class.getClassLoader();//NullPointerException
        classLoader = classLoader.getParent();
        System.out.println("classLoader's parent is:" + classLoader.toString());
        classLoader = classLoader.getParent();
        System.out.println("classLoader's parent's parent is:" + classLoader.toString());
    }
}
