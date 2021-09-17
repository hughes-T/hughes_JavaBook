package com.hughes.design.pattren.singleton;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @author hughes-T
 * @since 2021/8/10 16:58
 */
public class SerializableSingletonTest {
    public static void main(String[] args) throws Exception {
        Object obj1 = SerializableSingleton.INSTANCE;
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("SerializableSingleton.obj"));
        oos.writeObject(obj1);
        oos.flush();
        oos.close();
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("SerializableSingleton.obj"));
        Object obj2 = ois.readObject();
        ois.close();
        System.out.println(obj1 == obj2);
    }
}
