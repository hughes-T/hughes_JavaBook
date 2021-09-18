package com.hughes.interfaceSegregation;

import com.hughes.interfaceSegregation.Iinterface.IAnimal;
import com.hughes.interfaceSegregation.Iinterface.IFly;

/**
 * @author hughes-T
 * @since 2021/8/6  9:06
 */
public class Bird implements IAnimal , IFly{
    @Override
    public void sire() {
        System.out.println("下蛋");
    }

    @Override
    public void flying() {
        System.out.println("飞");
    }
}
