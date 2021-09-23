## 工厂模式

### 定义

- 对实体的扩展行为开放，对修改行为关闭；
- 核心思想是面向抽象编程，用抽象构建框架，用实现扩展细节

------
### 举例

#### 初始场景

玩家 Player 有获得经验的方法addExperience：

```java
public interface Player {

    /**
     * 新增经验
     */
    void addExperience(int experience);
    
}

@ToString
public class PlayerImpl implements Player {

    private final String name;
    private int experience;

    public PlayerImpl(String name) {
        this.experience = 0;
        this.name = name;
    }

    @Override
    public void addExperience(int experience) {
        this.experience = this.experience + experience;
    }

}
```

现在新增一个需求，活动期间经验翻倍，那么如果是为了快速实现，于是直接修改addExperience方法：

```java
public void addExperience(int experience) {
    this.experience = this.experience + experience * 2;
}
```

思考一下，修改后的addExperience方法的功能已经失去它原有的意义

#### 场景优化

新增addActiveExperience方法，以遵循开闭原则：

```java
public class ActivePlayer extends PlayerImpl{

    public ActivePlayer(String name) {
        super(name);
    }

    public void addActiveExperience(int experience) {
        super.addExperience(experience * 2);
    }
}
```



------

### 个人心得

- 开闭原则是面向对象编程最基础的原则，指导我们构建稳定灵活的系统，减少维护带来的新的风险
- **在面临细节实现时，应当尽量避免修改原来的细节，而是扩展的形式**
- 这考验一个人对于事物规律总结和归纳的能力，不论是开发还是现实，每一步细节都应以更高的视角去思考、兼顾




