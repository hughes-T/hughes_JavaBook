package com.hughes.design.pattren.factory.factory;

import com.hughes.design.pattren.factory.domain.IEquipment;
import com.hughes.design.pattren.factory.domain.ISkill;

/**
 * @author hughes-T
 * @since 2021/8/9  14:22
 */
public abstract class NpcAbstractFactory {
    protected void init(){
        System.out.println("初始化资源");
    }
    protected abstract IEquipment createEquipment();
    protected abstract ISkill createSkill();
}
