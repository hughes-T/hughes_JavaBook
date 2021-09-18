package com.hughes.desginPrinciples.openClosed;

/**
 * 测试类
 *
 * @author hughes-T
 * @since 2021/8/3  17:24
 */
public class OCPTest {

    public static void main(String[] args) {
        //step 1
        Player player1 = new PlayerImpl("张三");
        player1.addExperience(100);
        System.out.println(player1.getName() + ":" + player1.getExperience());
        //stp2
        ActivePlayer player2 = new ActivePlayer("李四");
        player2.addActiveExperience(100);
        System.out.println(player2.getName() + ":" + player2.getExperience());
    }

}
