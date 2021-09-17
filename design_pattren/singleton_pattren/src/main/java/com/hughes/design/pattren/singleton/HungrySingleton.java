package com.hughes.design.pattren.singleton;

/**
 * 饿汉式单例
 * @author hughes-T
 * @since 2021/8/10 15:57
 */
public class HungrySingleton {

    private static final HungrySingleton INSTANCE = new HungrySingleton();

    private HungrySingleton(){}

    public static HungrySingleton getInstance(){
        return INSTANCE;
    }
}
