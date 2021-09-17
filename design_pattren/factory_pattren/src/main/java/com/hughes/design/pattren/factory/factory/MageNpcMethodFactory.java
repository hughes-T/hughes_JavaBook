package com.hughes.design.pattren.factory.factory;

import com.hughes.design.pattren.factory.domain.INpc;
import com.hughes.design.pattren.factory.domain.MageNpc;

/**
 * @author hughes-T
 * @since 2021/8/9  13:50
 */
public class MageNpcMethodFactory implements INpcMethodFactory{
    @Override
    public INpc create() {
        return new MageNpc();
    }
}
