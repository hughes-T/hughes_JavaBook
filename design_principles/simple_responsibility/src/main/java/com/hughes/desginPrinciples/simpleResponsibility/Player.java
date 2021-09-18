package com.hughes.desginPrinciples.simpleResponsibility;

/**
 * @author hughes-T
 * @since 2021/8/5  9:01
 */
public class Player {
    public void attack(String playerProperty){
        if ("fire".equals(playerProperty)){
            System.out.println("火属性攻击");
        }else if ("water".equals(playerProperty)){
            System.out.println("水属性攻击");
        }
    }
}
