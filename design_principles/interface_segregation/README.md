## 接口隔离原则Interface-Segregation-Principle

### 定义

- 尽量细化接口，建立单一的接口，类与类之间的依赖应建立在最小的接口上

------
### 举例

#### 初始场景

定义一个动物接口IAnimal，定义繁衍、飞行、奔跑、游泳四种方法，那么Dog类实现接口时，无法实现飞行和游泳

```java
public interface IAnimal {
    void sire();
    void flying();
    void running();
    void swimming();
}
public class Dog implments IAnimal {

    public void sire() {
        System.out.println("下崽");
    }

    public void running() {
        System.out.println("奔跑");
    }
    
    public void flying(){
        System.out.println("???")
    }
    public void swimming(){
         System.out.println("???")
    }
}

```

或许定义“会飞的动物”、“会游泳的动物”这一系统的接口来避免这种错误，但这并不优雅；

我们强调**面向抽象编程**，动物就是动物，这个场景的IAnimal 接口应当只有一个方法，就是sire繁衍方法；

其他的飞行、游泳等是独立的功能，可以把它定义成一个运动形式，或者单独一个接口

#### 场景优化



```java
public interface IAnimal {
    void sire();
}

public interface IFly {
    void flying();
}

public class Bird implements IAnimal , IFly{
    @Override
    public void sire() {
        System.out.println("下蛋");
    }

    @Override
    public void flying() {
        System.out.println("飞");
    }
}
```



------

### 个人心得

- 接口隔离原则体现了高内聚低耦合的设计思想，增加了系统的扩展性和维护性；
- 和开闭原则相比，接口隔离原则一样也要求面对具体业务时的抽象能力，设计接口时一定要深入思路业务模型，同时对于可能发生的变动有预见性




