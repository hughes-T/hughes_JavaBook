## 依赖倒置原则 Dependence-Inversion-Principle

### 定义

- 在设计代码时，高层不应该依赖底层，抽象不应依赖细节，而是细节应当依赖抽象

------
### 举例

#### 初始场景

客户端Client调度任务处理器TaskHandler中的方法：

```java
public class Client {
    public static void main(String[] args) {
        userTaskHandler(new TaskHandler());
    }
    
    private static void userTaskHandler(TaskHandler taskHandler){
        taskHandler.handlerBehavior();
        taskHandler.handlerDiscern();
    }
}
public class TaskHandler {

    public void handlerBehavior(){
        System.out.println("处理行为型任务");
    }

    public void handlerDiscern(){
        System.out.println("处理检测型任务");
    }

}

```

思考一下，Client做为上层，因为依赖TaskHandler而导致耦合，被实现所受制。另外，我们会意识到handlerBehavior和handlerDiscern存在某种规律，它们的目的是相同的，只是被“强制赋予业务含义”。

#### 场景优化

抽象TaskHandler，使Client依赖抽象，同时实现也被抽象约束，达到依赖倒置的目的：

```java
public class Client {
    public static void main(String[] args) {
        userTaskHandler(new BehaviorTaskHandler());
        userTaskHandler(new DiscernTaskHandler());
    }

    private static void userTaskHandler(TaskHandler taskHandler){
        taskHandler.handler();
    }
}

public interface TaskHandler {

    void handler();
}

public class BehaviorTaskHandler implements TaskHandler{
    @Override
    public void handler() {
        System.out.println("处理行为型任务");
    }
}

public class DiscernTaskHandler implements TaskHandler{
    @Override
    public void handler() {
        System.out.println("处理检测型任务");
    }
}
```

#### 场景优化2
使用枚举和spring ioc 优化实现方式
```java
public interface ITaskHandlerWithEnum {

    TaskHandlerType getType();

    void handler();

    static ITaskHandlerWithEnum getInstance(TaskHandlerType type){
        ApplicationContext context = ApplicationContextUtil.getContext();
        Map<String, ITaskHandlerWithEnum> beans = context.getBeansOfType(ITaskHandlerWithEnum.class);
        for (Map.Entry<String, ITaskHandlerWithEnum> entry : beans.entrySet()) {
            if (type.equals(entry.getValue().getType())){
                return entry.getValue();
            }
        }
        throw new RuntimeException(String.format("can not find %s instance", type.name()));
    }

    enum TaskHandlerType{
        BEHAVIOR,DISCERN
    }

}
```

*如果你对于这个举例感到疑惑，并未看到优化带来的效果，请联想当一个系统的依赖层次很深时，每个上层受制于下层，带来牵一发而动全身的滋味可是很不好受的*

------

### 个人心得

- 依赖倒置原则能够实现类与类之间的解耦，降低代码修改时带来的风险
- 高层调用底层，通过抽象底层达到依赖倒置，这个抽象好比一个灵活的粘合剂，使得本来僵硬的一段东西拥有了灵活的关节，所以我们对于事物的思考要具有灵活性，“一个直男不会是一个好的程序员”

  




