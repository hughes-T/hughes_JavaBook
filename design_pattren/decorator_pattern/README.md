## 装饰器模式

### 定义

- 动态地给一个对象添加一些额外的职责
- 核心是透明动态地扩展功能,是比继承更弹性的替代方案

------
### 举例

#### 通用结构

- 抽象组件Component:被包装抽象类或接口 
- 具体组件ConcreteComponent:被修饰具体类
- 抽象装饰器Decorator:抽象装饰接口
- 具体装饰器类 ConcreteDecorator: 一般构造方法为被装饰类,理论上一个具体装饰器扩展一种功能


```java
public abstract class Component {
    abstract void operation();
}

public class ConcreteComponent extends Component{

    @Override
    void operation() {
        System.out.println("Component Operation ...");
    }
}

public abstract class Decorator extends Component{

    protected Component component;

    protected Decorator(Component component){
        this.component = component;
    }

    abstract void operation();
}

public class ConcreteDecoratorA extends Decorator{

    protected ConcreteDecoratorA(Component component) {
        super(component);
    }

    @Override
    void operation() {
        operationA();
        component.operation();
    }

    void operationA(){
        System.out.println("ConcreteDecoratorA operationA");
    }
}
```


- 装饰器模式遵循里氏替换原则、依赖倒置原则，具备很好的扩展性
- 与代理模式相比，装饰器的结构与静态代理一致，但是代理模式强调行为控制，装饰器模式强调功能扩展，代理模式不一定是is-a的关系，装饰器模式满足is-a的关系

#### 装饰Logger

通过装饰器，使得logger打印json格式的日志

```java
public abstract class LoggerDecorator implements Logger {

    protected Logger logger;

    protected LoggerDecorator(Logger logger){
        this.logger = logger;
    }

    public abstract void info(String s);

    public abstract void error(String s);
    
    //...省略其他实现
}

public class JsonLogger extends LoggerDecorator{

    protected JsonLogger(Logger logger) {
        super(logger);
    }

    @Override
    public void info(String s) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("MSG",s);
        logger.info(jsonObject.toJSONString());
    }

    @Override
    public void error(String s) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("MSG",s);
        logger.error(jsonObject.toJSONString());
    }
}

public class JsonLoggerFactory {
    public static JsonLogger getLogger(Class clazz){
        Logger logger = LoggerFactory.getLogger(clazz);
        return new JsonLogger(logger);
    }
}

public class Client {
   private static final JsonLogger logger = JsonLoggerFactory.getLogger(Client.class);
    public static void main(String[] args) {
        logger.info("哈哈哈");
        logger.error("呜呜呜");
    }
}
```

#### 源码应用

JDK的IO体系

![](..\..\resources\image\io_uml.jpg)

Spring中处理事物缓存的类TransactionAwareCacheDecorator

```java
public class TransactionAwareCacheDecorator implements Cache {
    private final Cache targetCache;

    public TransactionAwareCacheDecorator(Cache targetCache) {
        Assert.notNull(targetCache, "Target Cache must not be null");
        this.targetCache = targetCache;
    }

    public Cache getTargetCache() {
        return this.targetCache;
    }
    //...
}
```

Spring MVC中 HttpHeadResponseDecorator

```java
public class HttpHeadResponseDecorator extends ServerHttpResponseDecorator {


	public HttpHeadResponseDecorator(ServerHttpResponse delegate) {
		super(delegate);
	}
    //...
}
```



Mybatis 中 org.apache.ibatis.cache.decorators 包下各种处理缓存的缓存装饰器类

![](E:\my_project\hughes\hughes_JavaBook\resources\image\mybatis_cache_uml.jpg)

------

### 个人心得

- 装饰器模式和继承相比，扩展功能更加动态透明
- 装饰器模式与代理模式结构一致，但体现的思想不同，一个强调功能扩展，一个强调行为控制


