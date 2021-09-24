package com.hughes.design.pattren.flyweight.general;

/**
 * 具体享元角色
 * @author hughes-T
 * @since 2021/9/22 16:37
 */
public class ConcreteFlyweight implements IFlyweight{

    private final String innerState;

    public ConcreteFlyweight(String innerState){
        this.innerState = innerState;
    }

    @Override
    public void operation(String existState) {
        System.out.println(String.format("Object Address: %s, innerState: %s, existState: %s",
                System.identityHashCode(this),this.innerState,existState));
    }
}
