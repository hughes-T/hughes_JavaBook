package com.hughes.design.pattren.factory.factory;

import com.hughes.design.pattren.factory.domain.IEquipment;
import com.hughes.design.pattren.factory.domain.ISkill;
import com.hughes.design.pattren.factory.domain.WarriorEquipment;
import com.hughes.design.pattren.factory.domain.WarriorSkill;

/**
 * @author hughes-T
 * @since 2021/8/9  14:45
 */
public class WarriorNpcAbstractFactory extends NpcAbstractFactory{
    @Override
    protected IEquipment createEquipment() {
        super.init();
        return new WarriorEquipment();
    }
    @Override
    protected ISkill createSkill() {
        super.init();
        return new WarriorSkill();
    }
}
