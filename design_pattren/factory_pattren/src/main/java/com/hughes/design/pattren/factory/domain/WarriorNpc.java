package com.hughes.design.pattren.factory.domain;

/**
 * @author hughes-T
 * @since 2021/8/9  10:34
 */
public class WarriorNpc implements INpc {
    @Override
    public void fight() {
        System.out.println("使用物理攻击");
    }
}
