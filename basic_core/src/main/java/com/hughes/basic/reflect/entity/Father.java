package com.hughes.basic.reflect.entity;

/**
 * @author hughes-T
 * @since 2021/12/9 13:37
 */
public class Father {

    public String faOpenFiled;

    private String faCloseFiled;

    public void faOpen(){
        System.out.println("父类公共方法");
    }

    private void faClose(){
        System.out.println("父类私有方法");
    }
}
