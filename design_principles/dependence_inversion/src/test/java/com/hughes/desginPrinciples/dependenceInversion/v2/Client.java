package com.hughes.desginPrinciples.dependenceInversion.v2;

/**
 * @author hughes-T
 * @since 2021/9/18 22:27
 */
public class Client {
    public static void main(String[] args) {
        userTaskHandler(new BehaviorTaskHandler());
        userTaskHandler(new DiscernTaskHandler());
    }

    private static void userTaskHandler(TaskHandler taskHandler){
        taskHandler.handler();
    }
}
