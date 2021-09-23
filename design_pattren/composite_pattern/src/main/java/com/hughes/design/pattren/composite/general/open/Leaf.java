package com.hughes.design.pattren.composite.general.open;

/**
 * @author hughes-T
 * @since 2021/9/23 10:19
 */
public class Leaf extends AbsComponent{

    public Leaf(String commonState) {
        super(commonState);
    }

    @Override
    public void operation() {
        System.out.println("leaf operation");
    }
}
