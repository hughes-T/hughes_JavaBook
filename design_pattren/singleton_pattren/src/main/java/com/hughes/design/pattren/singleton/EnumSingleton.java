package com.hughes.design.pattren.singleton;

import java.io.Serializable;

/**
 * 枚举式单例
 * @author hughes-T
 * @since 2021/8/11 9:32
 */
public enum EnumSingleton implements Serializable {
    INSTANCE;
    private Object data;
    public Object getData() {
        return data;
    }
    public void setData(Object data) {
        this.data = data;
    }
    public static EnumSingleton getInstance(){
        return INSTANCE;
    }
}
