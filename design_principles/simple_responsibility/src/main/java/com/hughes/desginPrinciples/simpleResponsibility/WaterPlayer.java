package com.hughes.desginPrinciples.simpleResponsibility;

/**
 * @author hughes-T
 * @since 2021/8/5  13:35
 */
public class WaterPlayer implements IPlayer{
    @Override
    public void attack() {
        System.out.println("进行水属性攻击");
    }
}
