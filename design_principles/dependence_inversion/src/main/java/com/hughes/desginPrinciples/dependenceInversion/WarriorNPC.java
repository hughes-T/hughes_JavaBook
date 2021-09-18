package com.hughes.desginPrinciples.dependenceInversion;

/**
 * @author hughes-T
 * @since 2021/8/4  11:30
 */
public class WarriorNPC implements NPC{

    @Override
    public void fire() {
        System.out.println("与战士NPC战斗...");
    }
}
