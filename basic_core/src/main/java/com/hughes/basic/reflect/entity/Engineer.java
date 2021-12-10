package com.hughes.basic.reflect.entity;

/**
 * @author hughes-T
 * @since 2021/12/9 10:35
 */
public class Engineer implements Employee{
    private  String name;
    public String getName() {
        return name;
    }
    public Engineer(String name) {
        this.name = name;
    }

    public Engineer() {
    }
}
