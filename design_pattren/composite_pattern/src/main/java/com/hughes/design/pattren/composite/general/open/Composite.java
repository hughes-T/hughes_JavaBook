package com.hughes.design.pattren.composite.general.open;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hughes-T
 * @since 2021/9/23 10:15
 */
public class Composite extends AbsComponent{

    private final List<AbsComponent> components = new ArrayList<>();

    public Composite(String commonState) {
        super(commonState);
    }

    @Override
    public void operation() {
        System.out.println("Composite operation");
    }

    @Override
    public AbsComponent getChild(int index) {
        return components.get(index);
    }

    @Override
    public void addChild(AbsComponent component) {
        components.add(component);
    }

    @Override
    public void removeChild(AbsComponent component) {
        components.remove(component);
    }
}
