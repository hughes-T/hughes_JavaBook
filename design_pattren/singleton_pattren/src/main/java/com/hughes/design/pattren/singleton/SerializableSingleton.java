package com.hughes.design.pattren.singleton;

import java.io.Serializable;

/**
 * @author hughes-T
 * @since 2021/8/10 16:57
 */
public class SerializableSingleton implements Serializable {
    public final static SerializableSingleton INSTANCE = new SerializableSingleton();
    private SerializableSingleton(){}
    private Object readResolve(){
        return INSTANCE;
    }
}
