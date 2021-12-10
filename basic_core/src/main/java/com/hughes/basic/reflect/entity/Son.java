package com.hughes.basic.reflect.entity;

/**
 * @author hughes-T
 * @since 2021/12/9 13:39
 */
public class Son extends Father {

    public String soOpenFiled;

    private String soCloseFiled;

    static public String name;

    public void sonOpen(){
        System.out.println("子类公共方法");
    }

    private void sonClose(){
        System.out.println("子类私有方法");
    }
}
