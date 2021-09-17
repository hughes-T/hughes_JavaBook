package com.hughes.design.pattren.factory.factory;

import com.hughes.design.pattren.factory.domain.INpc;
import com.hughes.design.pattren.factory.domain.WarriorNpc;

/**
 * @author hughes-T
 * @since 2021/8/9  13:52
 */
public class WarriorNpcMethodFactory implements INpcMethodFactory{
    @Override
    public INpc create() {
        return new WarriorNpc();
    }
}
