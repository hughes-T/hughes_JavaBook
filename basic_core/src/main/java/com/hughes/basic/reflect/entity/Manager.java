package com.hughes.basic.reflect.entity;

/**
 * @author hughes-T
 * @since 2021/12/9 10:36
 */
public class Manager implements Employee{
    private final String name;
    public Manager(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
}
