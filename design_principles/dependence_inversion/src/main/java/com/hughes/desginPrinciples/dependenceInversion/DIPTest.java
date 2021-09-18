package com.hughes.desginPrinciples.dependenceInversion;

/**
 * @author hughes-T
 * @since 2021/8/4  11:17
 */
public class DIPTest {

    public static void main(String[] args) {
        Player player = new Player();
        //step1
//        player.fireToMageNPC();
//        player.fireToWarriorNPC();
        player.fireToNPC(new MageNPC());
        player.fireToNPC(new WarriorNPC());
    }
}
