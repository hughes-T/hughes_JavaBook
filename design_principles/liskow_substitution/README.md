## 里氏替换原则 Liskow_Substitution-Principle

### 定义

- 子类对象能够替换父类对象的任何对方且使得程序不变

------
### 举例

#### 反例场景

定义长方形，定义正方形为长方形的子类

```java
@Data
public class Rectangle {

    private int width;

    private int high;
}

public class Square extends Rectangle{

    private int length;

    @Override
    public int getWidth() {
        return length;
    }

    @Override
    public void setWidth(int width) {
       this.length = width;
    }

    @Override
    public int getHigh() {
        return length;
    }

    @Override
    public void setHigh(int high) {
        this.length = high;
    }
}
```

直观上说”正方形是特殊的长方形“，但遇到如下resize方法的情况，程序将会陷入死循环

```java
   /**
     * 使得长大于宽
     */
    public void resize(Rectangle rectangle){
        while (rectangle.getHigh() >= rectangle.getWidth()){
            rectangle.setHigh(rectangle.getHigh() + 1);
            System.out.println("High:"+ rectangle.getHigh() + " " + "Width:"+ rectangle.getWidth());
        }

    }
```

------

### 个人心得

- 里氏替换原则是为了防止继承泛滥，因为在继承是强耦合的一种关系，当不得已要使用继承时，应当遵从里氏替换原则来防止不可控的风险


