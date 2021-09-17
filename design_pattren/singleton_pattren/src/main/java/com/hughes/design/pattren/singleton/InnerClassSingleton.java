package com.hughes.design.pattren.singleton;

/**
 * 内部类单例
 *
 * @author hughes-T
 * @since 2021/8/10 16:40
 */
public class InnerClassSingleton {
    private InnerClassSingleton() {
        if (SingletonHolder.INSTANCE != null){
            throw new RuntimeException("不允许被反射创建!");
        }
    }
    public static InnerClassSingleton getInstance() {
        return SingletonHolder.INSTANCE;
    }
    private static class SingletonHolder {
        private static final InnerClassSingleton INSTANCE = new InnerClassSingleton();
    }
}
