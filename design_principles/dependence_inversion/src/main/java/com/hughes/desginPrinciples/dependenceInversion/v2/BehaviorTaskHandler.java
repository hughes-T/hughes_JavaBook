package com.hughes.desginPrinciples.dependenceInversion.v2;

/**
 * @author hughes-T
 * @since 2021/9/18 22:25
 */
public class BehaviorTaskHandler implements TaskHandler{
    @Override
    public void handler() {
        System.out.println("处理行为型任务");
    }
}
