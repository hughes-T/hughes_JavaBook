package com.hughes.util;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author hughes-T
 * @since 2021/10/12 10:42
 */
public class ClassUtil {

    /**
     * 获取泛型上的class
     * @author hughes-T 2021/10/12 10:43
     */
    public static Class<?>[] getActualClasses(Class<?> clazz){
        Type[] types = ((ParameterizedType) clazz.getGenericSuperclass()).getActualTypeArguments();
        return (Class<?>[]) types;
    }
}
