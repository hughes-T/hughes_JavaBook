package com.hughes.design.pattren.factory.factory;

import com.hughes.design.pattren.factory.domain.IEquipment;
import com.hughes.design.pattren.factory.domain.ISkill;
import com.hughes.design.pattren.factory.domain.MageEquipment;
import com.hughes.design.pattren.factory.domain.MageSkill;

/**
 * @author hughes-T
 * @since 2021/8/9  14:44
 */
public class MageNpcAbstractFactory extends NpcAbstractFactory{
    @Override
    protected IEquipment createEquipment() {
        super.init();
        return new MageEquipment();
    }
    @Override
    protected ISkill createSkill() {
        super.init();
        return new MageSkill();
    }
}
