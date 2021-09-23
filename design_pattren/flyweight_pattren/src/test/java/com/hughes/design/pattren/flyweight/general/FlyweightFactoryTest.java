package com.hughes.design.pattren.flyweight.general;

import org.junit.Test;

/**
 * @author hughes-T
 * @since 2021/9/22 16:47
 */
public class FlyweightFactoryTest {

    @Test
    public void testGetFlyweight(){
        IFlyweight flyweight = FlyweightFactory.getFlyweight("a");
        IFlyweight flyweight2 = FlyweightFactory.getFlyweight("a");
        IFlyweight flyweight3 = FlyweightFactory.getFlyweight("b");
        flyweight.operation("1");
        flyweight2.operation("2");
        flyweight3.operation("3");
    }
}
