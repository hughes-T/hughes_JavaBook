package com.hughes.design.pattren.singleton;

/**
 * @author hughes-T
 * @since 2021/8/10 16:12
 */
public class LazySingletonTest {
    public static void main(String[] args) {
        new Thread(()->{
            LazySingleton instance = LazySingleton.getInstance();
            System.out.println(Thread.currentThread().getName() + ":" + instance);
        }).start();

        new Thread(()->{
            LazySingleton instance = LazySingleton.getInstance();
            System.out.println(Thread.currentThread().getName() + ":" + instance);
        }).start();
    }
}
