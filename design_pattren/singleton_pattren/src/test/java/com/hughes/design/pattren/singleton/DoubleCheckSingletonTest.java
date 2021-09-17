package com.hughes.design.pattren.singleton;

/**
 *
 * @author hughes-T
 * @since 2021/8/10 16:28
 */
public class DoubleCheckSingletonTest {
    public static void main(String[] args) {
        new Thread(()->{
            DoubleCheckSingleton instance = DoubleCheckSingleton.getInstance();
            System.out.println(Thread.currentThread().getName() + ":" + instance);
        }).start();

        new Thread(()->{
            DoubleCheckSingleton instance = DoubleCheckSingleton.getInstance();
            System.out.println(Thread.currentThread().getName() + ":" + instance);
        }).start();
    }
}
