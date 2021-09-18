package com.hughes.desginPrinciples.dependenceInversion.v1;

/**
 * @author hughes-T
 * @since 2021/9/18 22:08
 */
public class Client {
    public static void main(String[] args) {
        userTaskHandler(new TaskHandler());
    }

    private static void userTaskHandler(TaskHandler taskHandler){
        taskHandler.handlerBehavior();
        taskHandler.handlerDiscern();
    }
}
