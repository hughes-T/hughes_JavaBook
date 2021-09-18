package com.hughes.interfaceSegregation;

import com.hughes.interfaceSegregation.Iinterface.IAnimal;
import com.hughes.interfaceSegregation.Iinterface.ISwim;

/**
 * @author hughes-T
 * @since 2021/8/6  9:13
 */
public class Fish implements IAnimal, ISwim {
    @Override
    public void sire() {
        System.out.println("产卵");
    }
    @Override
    public void swimming() {
        System.out.println("游泳");
    }
}
