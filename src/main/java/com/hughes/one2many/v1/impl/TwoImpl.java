package com.hughes.one2many.v1.impl;

import org.springframework.stereotype.Service;

/*
 * @Description
 * @Author hughesT
 * @Date 2022/3/15 20:03
 */
@Service
public class TwoImpl extends AbsImpl {

    @Override
    public final Type getType() {
        return Type.TWO;
    }

}
