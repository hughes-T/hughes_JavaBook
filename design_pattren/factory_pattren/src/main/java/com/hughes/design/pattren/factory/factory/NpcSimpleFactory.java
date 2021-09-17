package com.hughes.design.pattren.factory.factory;

import com.hughes.design.pattren.factory.domain.INpc;

/**
 * @author hughes-T
 * @since 2021/8/9  10:35
 */
public class NpcSimpleFactory {
    public static INpc createNpc(Class<? extends INpc> clazz){
        try {
            if (clazz != null){
                return clazz.newInstance();
            }
        }  catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
