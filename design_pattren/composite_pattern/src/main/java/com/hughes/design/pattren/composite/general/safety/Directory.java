package com.hughes.design.pattren.composite.general.safety;

/**
 * 目录
 * @author hughes-T
 * @since 2021/9/23 10:25
 */
public abstract class Directory {

    protected String name;

    public Directory(String name) {
        this.name = name;
    }

    public abstract void show();

}
