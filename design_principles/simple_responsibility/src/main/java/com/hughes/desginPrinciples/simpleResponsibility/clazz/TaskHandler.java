package com.hughes.desginPrinciples.simpleResponsibility.clazz;

/**
 * @author hughes-T
 * @since 2021/9/22 9:22
 */
public class TaskHandler {

    public void handler(String taskType){
        if ("行为型".equals(taskType)){
            System.out.println("保存图片");
        }else {
            System.out.println("不保存图片");
        }
    }
}
