package com.hughes.design.pattren.factory.factory;

import com.hughes.design.pattren.factory.domain.INpc;
import com.hughes.design.pattren.factory.domain.MageNpc;
import com.hughes.design.pattren.factory.domain.WarriorNpc;

/**
 * @author hughes-T
 * @since 2021/8/9  10:56
 */
public class TestSimpleFactory {
    public static void main(String[] args) {
        INpc npc1 = NpcSimpleFactory.createNpc(MageNpc.class);
        INpc npc2 = NpcSimpleFactory.createNpc(WarriorNpc.class);
        if (npc1 != null) {
            npc1.fight();
        }
        if (npc2 != null) {
            npc2.fight();
        }
    }
}
