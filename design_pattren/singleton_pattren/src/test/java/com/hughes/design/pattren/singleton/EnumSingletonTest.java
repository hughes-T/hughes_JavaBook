package com.hughes.design.pattren.singleton;

import java.io.*;
import java.lang.reflect.Constructor;

/**
 * @author hughes-T
 * @since 2021/8/11 9:35
 */
public class EnumSingletonTest {
    public static void main(String[] args) throws Exception {
        EnumSingleton obj1 = EnumSingleton.getInstance();
        //尝试序列化破坏
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(obj1);
        oos.flush();
        oos.close();
        ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(bos.toByteArray()));
        Object obj3 = ois.readObject();
        System.out.println(obj1 == obj3);//true
        //尝试反射破坏
        Class<EnumSingleton> clazz = EnumSingleton.class;
        Constructor<EnumSingleton> constructor = clazz.getDeclaredConstructor(String.class,int.class);
        constructor.setAccessible(true);
        EnumSingleton obj2 = constructor.newInstance("obj2", 2); //Cannot reflectively create enum objects
        System.out.println(obj1 == obj2);
    }
}
