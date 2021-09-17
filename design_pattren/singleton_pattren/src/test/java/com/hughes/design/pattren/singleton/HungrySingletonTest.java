package com.hughes.design.pattren.singleton;

/**
 * @author hughes-T
 * @since 2021/8/10 16:00
 */
public class HungrySingletonTest {
    public static void main(String[] args) {
        new Thread(()->{
            HungrySingleton instance = HungrySingleton.getInstance();
            System.out.println(Thread.currentThread().getName() + ":" + instance);
        }).start();

        new Thread(()->{
            HungrySingleton instance = HungrySingleton.getInstance();
            System.out.println(Thread.currentThread().getName() + ":" + instance);
        }).start();
    }
}
