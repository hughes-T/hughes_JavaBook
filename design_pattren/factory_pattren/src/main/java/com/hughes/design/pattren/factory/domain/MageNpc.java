package com.hughes.design.pattren.factory.domain;

/**
 * @author hughes-T
 * @since 2021/8/9  10:33
 */
public class MageNpc implements INpc {

    @Override
    public void fight() {
        System.out.println("使用魔法攻击");
    }
}
