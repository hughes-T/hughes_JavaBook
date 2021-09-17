package com.hughes.design.pattren.singleton;

/**
 * @author hughes-T
 * @since 2021/8/11 14:04
 */
public class ThreadLocalSingletonTest {
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName() + ":" + ThreadLocalSingleton.getInstance());
        System.out.println(Thread.currentThread().getName() + ":" + ThreadLocalSingleton.getInstance());
        System.out.println(Thread.currentThread().getName() + ":" + ThreadLocalSingleton.getInstance());
        new Thread(()-> {
            System.out.println(Thread.currentThread().getName() + ":" + ThreadLocalSingleton.getInstance());
            System.out.println(Thread.currentThread().getName() + ":" + ThreadLocalSingleton.getInstance());
            System.out.println(Thread.currentThread().getName() + ":" + ThreadLocalSingleton.getInstance());
        }).start();
        new Thread(()-> {
            System.out.println(Thread.currentThread().getName() + ":" + ThreadLocalSingleton.getInstance());
            System.out.println(Thread.currentThread().getName() + ":" + ThreadLocalSingleton.getInstance());
            System.out.println(Thread.currentThread().getName() + ":" + ThreadLocalSingleton.getInstance());
        }).start();
    }
}
