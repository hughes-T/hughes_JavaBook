package com.hughes.design.pattren.composite.general.safety;

/**
 * @author hughes-T
 * @since 2021/9/23 10:26
 */
public class File extends Directory {

    public File(String name) {
        super(name);
    }

    @Override
    public void show() {
        System.out.println(name);
    }
}
