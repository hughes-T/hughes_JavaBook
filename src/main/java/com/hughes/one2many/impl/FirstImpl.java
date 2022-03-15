package com.hughes.one2many.impl;

import org.springframework.stereotype.Service;

/*
 * @Description
 * @Author hughesT
 * @Date 2022/3/15 20:03
 */
@Service
public class FirstImpl extends AbsImpl {

    @Override
    public final Type getType() {
        return Type.FIRST;
    }

}
