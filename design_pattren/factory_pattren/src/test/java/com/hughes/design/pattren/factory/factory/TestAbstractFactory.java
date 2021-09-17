package com.hughes.design.pattren.factory.factory;

/**
 * @author hughes-T
 * @since 2021/8/9  14:47
 */
public class TestAbstractFactory {
    public static void main(String[] args) {
        NpcAbstractFactory factory = new MageNpcAbstractFactory();
        factory.createEquipment().note();
        factory.createSkill().note();
    }
}
