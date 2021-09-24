## 享元模式

### 定义

- 使用共享对象有效地支持大量的细粒度对象,类似线程池,本质是缓存共享对象

------
### 举例

#### 通用结构

- 抽象享元角色Flyweight：享元对象接口，定义对象的外部状态和内部状态
- 具体享元角色Concrete Flyweight：享元对象实现，该角色的内部状态处理应于外部状态无关
- 享元工厂Flyweight Factory： 负责管理享元对象池和创建享元对象

```java
public interface IFlyweight {
    void operation(String existState);
}

public class ConcreteFlyweight implements IFlyweight{

    private final String innerState;

    public ConcreteFlyweight(String innerState){
        this.innerState = innerState;
    }

    @Override
    public void operation(String existState) {
        System.out.println(String.format("Object Address: %s, innerState: %s, existState: %s",
                System.identityHashCode(this),this.innerState,existState));
    }
}

public class FlyweightFactory {

    private static final Map<String,IFlyweight> pool = new ConcurrentHashMap<>();

    public static IFlyweight getFlyweight(String innerState){
        if (!pool.containsKey(innerState)){
            pool.put(innerState,new ConcreteFlyweight(innerState));
        }
        return pool.get(innerState);
    }
}
public class Test {

    @Test
    public void testGetFlyweight(){
        IFlyweight flyweight = FlyweightFactory.getFlyweight("a");
        IFlyweight flyweight2 = FlyweightFactory.getFlyweight("a");
        IFlyweight flyweight3 = FlyweightFactory.getFlyweight("b");
        flyweight.operation("1");
        flyweight2.operation("2");
        flyweight3.operation("3");
    }
}
```

#### 源码应用

JDK中的String：

JVM的字符串常量保存于字符串常量池中，JDK 7.0版本由永生代迁至堆中。

如果以字面量的形式创建String，JVM编译器就会把它保存在字符串常量池中，常量池中有且只有一份相同的字面量，如果有其他相同的字面量，则会返回这个引用

```java
  public static void main(String[] args) {
        String s1 = "hello";
        String s2 = "hello";
        String s3 = "hel" + "lo";
        String s4 = "hel";
        String s5 = "lo";
        String s6 = s4 + s5;
        final String s7 = "hel";
        final String s8 = "lo";
        String s9 = s7 + s8;
        String s10 = new String("hello");
        String s11 = s10.intern();
        System.out.println(s1 == s2);
        System.out.println(s1 == s3);
        System.out.println(s1 == s6);
        System.out.println(s1 == s9);
        System.out.println(s1 == s10);
        System.out.println(s1 == s11);
    }
```

JDK中的Interger:

Interger内部缓存了一个-128 至127的常量池，valueOf方法会返回在这期间的值

```java
public static void main(String[] args) {
    Integer a = Integer.valueOf(100);
    Integer b = 100;
    Integer c = Integer.valueOf(128);
    Integer d = 128;
    System.out.println(a == b);
    System.out.println(c == d);
}
```

------

### 个人心得

- 适合需要大量创建对象的场景，降低系统内存消耗
- 需要关注对象的内外部状态、线程安全等问题，复杂度增加




