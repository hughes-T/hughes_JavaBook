package com.hughes.design.pattren.singleton;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 容器式单例
 * @author hughes-T
 * @since 2021/8/11 10:40
 */
public class ContainerSingleton {
    private static final Map<String,Object> ioc = new ConcurrentHashMap<>();
    public static Object getBean(String className) throws Exception{
        if (!ioc.containsKey(className)){
            synchronized (ioc){
                if (!ioc.containsKey(className)){
                    Object obj = Class.forName(className).newInstance();
                    ioc.put(className,obj);
                }
            }
        }
        return ioc.get(className);
    }
}
