package com.hughes.desginPrinciples.dependenceInversion;

/**
 * @author hughes-T
 * @since 2021/8/4  11:18
 */
public class Player {
    //step1
//    public void fireToMageNPC(){
//        System.out.println("与法师NPC战斗...");
//    }
//
//    public void fireToWarriorNPC(){
//        System.out.println("与战士NPC战斗...");
//    }
    public void fireToNPC(NPC npc){
        npc.fire();
    }
}
