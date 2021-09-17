package com.hughes.design.pattren.factory.factory;

/**
 * @author hughes-T
 * @since 2021/8/9  13:53
 */
public class TestMethodFactory {
    public static void main(String[] args) {
        INpcMethodFactory factory = new WarriorNpcMethodFactory();
        factory.create().fight();
    }
}
