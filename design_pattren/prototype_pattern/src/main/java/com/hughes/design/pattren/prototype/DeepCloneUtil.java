package com.hughes.design.pattren.prototype;

import java.io.*;

/**
 * @author hughes-T
 * @since 2021/8/11 15:08
 */
public class DeepCloneUtil {
    public static Object deepClone(Object prototypeObj) throws IOException, ClassNotFoundException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(prototypeObj);
        oos.flush();
        oos.close();
        ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(bos.toByteArray()));
        Object cloneObj = ois.readObject();
        ois.close();
        return cloneObj;
    }
}
