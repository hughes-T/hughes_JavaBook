package com.hughes.design.pattren.singleton;

/**
 * 懒汉式单例
 * @author hughes-T
 * @since 2021/8/10 16:09
 */
public class LazySingleton {
    private static LazySingleton instance;
    private LazySingleton(){}
    public static LazySingleton getInstance(){
        if (instance == null){
            instance = new LazySingleton();
        }
        return instance;
    }
}
