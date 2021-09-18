package com.hughes.desginPrinciples.dependenceInversion;

/**
 * @author hughes-T
 * @since 2021/8/4  11:28
 */
public class MageNPC implements NPC{
    @Override
    public void fire() {
        System.out.println("与法师战斗...");
    }
}
