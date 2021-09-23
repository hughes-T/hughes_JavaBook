package com.hughes.design.pattren.flyweight.general;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 享元对象工厂
 * @author hughes-T
 * @since 2021/9/22 16:43
 */
public class FlyweightFactory {

    private static final Map<String,IFlyweight> pool = new ConcurrentHashMap<>();

    public static IFlyweight getFlyweight(String innerState){
        if (!pool.containsKey(innerState)){
            //享元的本质是缓存到达重复利用，因此不用保证创建对象的唯一性，保证创建对象的唯一性是单例模式
//            synchronized (pool){
//                if (!pool.containsKey(innerState)){
//                    pool.put(innerState,new ConcreteFlyweight(innerState));
//                }
//            }
            pool.put(innerState,new ConcreteFlyweight(innerState));
        }
        return pool.get(innerState);
    }
}
