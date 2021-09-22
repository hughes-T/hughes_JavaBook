

## 迪米特法则 Law-of-Demeter

### 定义

- 一个单元应当对其他单元保持最少的了解，又叫最少知道原则
- 强调只与朋友(成员变量/方法参数类型/返回类型)进行交流,不依赖陌生人

------
### 举例

#### 初始场景

老板命令员工

```java
public class Leader {

    public void commandCheckNum(Employee employee){
        List<Product> products = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            products.add(new Product());
        }
        employee.checkNum(products);
    }
}

public class Employee {
    public void checkNum(List<Product> products) {
        System.out.println("检测数量为:" + products.size());
    }
}
```

#### 场景优化

```java
public class Leader {
    public void commandCheckNum(Employee employee){
        employee.checkNum();
    }
}

public class Employee {
    public void checkNum() {
        List<Product> products = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            products.add(new Product());
        }
        System.out.println("检测数量为:" + products.size());
    }
}
```

前后结构对比

![](..\..\resources\image\law_of_demeter_uml.jpg)

------

### 个人心得

- 迪米特法则能够减少类之间的依赖，降低系统改动的影响范围
- 尽量遵守但不要刻意为之，如开闭原则、依赖倒置原则等都是顶层结构设计时需要兼顾的，迪米特法则是对具体行为进行约束，因此优先级要低于它们




