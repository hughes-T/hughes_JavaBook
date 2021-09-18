package com.hughes.desginPrinciples.simpleResponsibility;

/**
 * @author hughes-T
 * @since 2021/8/5  13:34
 */
public class FirePlayer implements IPlayer{
    @Override
    public void attack() {
        System.out.println("进行火属性攻击");
    }
}
