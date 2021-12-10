# 反射

> "反射是一种功能强大且复杂的机制，主要面向工具构造者而不是应用程序员"

## 作用

**1、在运行时期分析类的能力**

```java
/**
* 运行时获取对象的类名
*/
static void useClazz01(){
    Employee e1 = new Manager("john");
    Employee e2 = new Engineer("jack");
    System.out.println(e1.getClass().getSimpleName() +" " + e1.getName());//Manager john
    System.out.println(e2.getClass().getSimpleName() +" " + e2.getName());//Engineer jack
}
```

**2、实现通用数组操作代码**

示例为使用反射实现通用数组的copy

```java
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
```

**3、利用Method对象，类似C++的函数指针**

```java
/**
 * 使用Method调用方法
 */
static void useMethod01() throws Exception{
    Employee employee = new Engineer("jhon");
    Method method = employee.getClass().getMethod("getName");
    method.invoke(employee);
}
```

## 优缺点

**优点在于灵活度**

示例使用传统方式选择创建对象和反射创建

```java
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
```

**缺点是运行慢**

示例创建对象耗时对比

```java
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
```

反射为什么会慢？

比如反射创建是调用了native方法，且每次都做了安全检查，比较耗时


## Class内部结构

**获取Class的四种方式**

```java
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
```

**内部构造**

- Filed 字段
- Method 方法
- Constructor 构建器
- Modifier 解析关键词

```java
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
```

