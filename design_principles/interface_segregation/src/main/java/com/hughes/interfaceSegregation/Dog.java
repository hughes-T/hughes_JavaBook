package com.hughes.interfaceSegregation;

import com.hughes.interfaceSegregation.Iinterface.IAnimal;
import com.hughes.interfaceSegregation.Iinterface.IRun;

/**
 * @author hughes-T
 * @since 2021/8/6  9:11
 */
public class Dog implements IAnimal, IRun {
    @Override
    public void sire() {
        System.out.println("下崽");
    }
    @Override
    public void running() {
        System.out.println("奔跑");
    }
}
