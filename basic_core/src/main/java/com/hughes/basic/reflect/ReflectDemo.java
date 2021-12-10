package com.hughes.basic.reflect;

import com.hughes.basic.reflect.entity.*;

import java.lang.reflect.*;

/**
 * @author hughes-T
 * @since 2021/12/8 14:47
 */
public class ReflectDemo {

    public static void main(String[] args) throws Exception{

    }

    /**
     * 运行时获取对象的类名
     */
    static void useClazz01(){
        Employee e1 = new Manager("john");
        Employee e2 = new Engineer("jack");
        System.out.println(e1.getClass().getSimpleName() +" " + e1.getName());//Manager john
        System.out.println(e2.getClass().getSimpleName() +" " + e2.getName());//Engineer jack
    }

    /**
     * 获取Class的四种方式
     */
    static void getClazz() throws Exception{
        //直接获取
        Class<Dog> clazz1 = Dog.class;
        //全路径获取
        Class<?> clazz2 = Class.forName("com.hughes.basic.reflect.entity.Dog");
        //对象获取
        Dog dog = new Dog();
        Class<?> clazz3 = dog.getClass();
        //使用类加载器
        Class<?> clazz4 = ClassLoader.getSystemClassLoader().loadClass("com.hughes.basic.reflect.entity.Dog");
        System.out.println(clazz1 == clazz2 && clazz3 == clazz4);
    }

    /**
     * 反射创建时间对比
     */
    static void cmpCreateTime() throws Exception{
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100000000; i++) {
            Engineer engineer = new Engineer();
        }
        System.out.println("正常创建耗时:" + (System.currentTimeMillis() - start));//3
        start = System.currentTimeMillis();
        for (int i = 0; i < 100000000; i++) {
            Engineer engineer = Engineer.class.newInstance();
        }
        System.out.println("反射创建耗时:" + (System.currentTimeMillis() - start));//177
    }

    /**
     * 通用数组操作
     */
    static void generalArrayOp() throws Exception{
        Employee[] arr = new Employee[2];
        Employee[] result;
        //result = (Employee[]) badCopy(arr, arr.length * 2);//ClassCastException 一个开始就是Object[]永远不能转换成Employee[]
        result = (Employee[]) goodCopy(arr, arr.length * 2);
    }

    private static Object[] badCopy(Object[] arr , int newLength){
        Object[] newArray = new Object[newLength];
        System.arraycopy(arr,0,newArray,0,arr.length);
        return newArray;
    }

    private static Object goodCopy(Object arr , int newLength){
        Class<?> cl = arr.getClass();
        if (!cl.isArray()) return null;
        Class<?> componentType = cl.getComponentType();
        int length = Array.getLength(arr);
        Object newArray = Array.newInstance(componentType, newLength);
        System.arraycopy(arr,0,newArray,0,Math.min(length,newLength));
        return newArray;
    }

    /**
     * 使用Method调用方法
     */
    static void useMethod01() throws Exception{
        Employee employee = new Engineer("jhon");
        Method method = employee.getClass().getMethod("getName");
        method.invoke(employee);
    }

    /**
     * 查看内部结构
     */
    static void qryInnerStructure() throws Exception{
        Class fa = Father.class;
        Class son = Son.class;
        Field[] fields = son.getFields();
        Field[] declaredFields = son.getDeclaredFields();
        Method[] methods = son.getMethods();//返回包含超类的所有公共方法
        Method[] declaredMethods = son.getDeclaredMethods();//返回该类或接口的所有方法,但不包含超类
        declaredMethods[0].setAccessible(true);
        Constructor[] constructors = son.getConstructors();
        Constructor[] declaredConstructors = son.getDeclaredConstructors();
        System.out.println(Modifier.toString(fa.getModifiers()));
        System.out.println(Modifier.isPublic(son.getModifiers()));
    }

    static Animals getInstanceByKey(String key){
        Animals animals = null;
        if ("Cat".equals(key)){
            animals = new Cat();
        }
        if ("Dog".equals(key)){
            animals = new Dog();
        }
        return animals;
    }
    static Animals getInstanceByKeyReflect(String key){
        Animals animals = null;
        String basePackage = "com.hughes.basic.reflect.entity";
        try {
            @SuppressWarnings("unchecked")
            Class<Animals> clazz = (Class<Animals>) Class.forName(basePackage + "." + key);
            animals = clazz.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return animals;
    }
}
