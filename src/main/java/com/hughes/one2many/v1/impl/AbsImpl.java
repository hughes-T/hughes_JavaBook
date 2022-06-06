package com.hughes.one2many.v1.impl;

import com.hughes.one2many.v1.IEnumOne2ManyDemo;

/*
 * @Description
 * @Author hughesT
 * @Date 2022/3/15 20:05
 */
public abstract class AbsImpl implements IEnumOne2ManyDemo {

    @Override
    public void method() {
        System.out.println(this.getClass().getSimpleName());
    }
}
