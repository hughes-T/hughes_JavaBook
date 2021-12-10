package com.hughes.basic.reflect.entity;

/**
 * @author hughes-T
 * @since 2021/12/8 14:26
 */
public class Cat implements Animals {
    @Override
    public void run() {
        System.out.println("猫在跑");
    }
}
