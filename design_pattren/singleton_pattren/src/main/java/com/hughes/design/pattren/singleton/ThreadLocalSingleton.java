package com.hughes.design.pattren.singleton;

/**
 * 线程级单例
 * @author hughes-T
 * @since 2021/8/11 13:49
 */
public class ThreadLocalSingleton {
    private static final ThreadLocal<ThreadLocalSingleton> LOCAL = new ThreadLocal<>();
    private ThreadLocalSingleton(){}
    public static ThreadLocalSingleton getInstance(){
        ThreadLocalSingleton instance = LOCAL.get();
        if (instance == null){
            instance = new ThreadLocalSingleton();
            LOCAL.set(instance);
        }
        return instance;
    }
}
