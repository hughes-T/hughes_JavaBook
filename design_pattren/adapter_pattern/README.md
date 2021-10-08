## 适配器模式

### 定义

- 将一个类的接口变成客户端所期待的另一种接口，从而使原本因接口不匹配无法工作的两个类能一起工作

------
### 举例

#### 现实生活

- 变压器、转换线等

#### 通用结构

- 目标角色 Target：期待的接口
- 原角色 Component：已存在于系统中的实例
- 适配器 Adapter ：将原角色转换为目标角色的实例

*如果不使用组合模式，树枝节点内部就需要维护多个集合存储其他对象层次，这种构建带来了巨大的复杂性和不可扩展性，同时客户端访问时还需要进行层次区分，也会影响到客户端的复杂性。**组合模式抽取了系统各个层次的共性行为**，这样树枝节点只需要维护一个集合即可存储系统所有层次的内容，同时客户端也无需区分该系统的层次*

透明模式：

```java
public abstract class AbsComponent {

    protected String commonState;

    public AbsComponent(String commonState){
        this.commonState = commonState;
    }

    public void operation(){
        throw new UnsupportedOperationException("不支持操作");
    }

    public AbsComponent getChild(int index){
        throw new UnsupportedOperationException("不支持获取子节点");
    }

    public void addChild(AbsComponent component){
        throw new UnsupportedOperationException("不支持添加操作");
    }

    public void removeChild(AbsComponent component){
        throw new UnsupportedOperationException("不支持删除操作");
    }
    
}
public class Composite extends AbsComponent{

    private final List<AbsComponent> components = new ArrayList<>();

    public Composite(String commonState) {
        super(commonState);
    }

    @Override
    public void operation() {
        System.out.println("Composite operation");
    }

    @Override
    public AbsComponent getChild(int index) {
        return components.get(index);
    }

    @Override
    public void addChild(AbsComponent component) {
        components.add(component);
    }

    @Override
    public void removeChild(AbsComponent component) {
        components.remove(component);
    }
}

public class Leaf extends AbsComponent{

    public Leaf(String commonState) {
        super(commonState);
    }

    @Override
    public void operation() {
        System.out.println("leaf operation");
    }
}
```

安全模式：

以文件管理为例

```java
public abstract class Directory {

    protected String name;

    public Directory(String name) {
        this.name = name;
    }

    public abstract void show();

}
public class File extends Directory {

    public File(String name) {
        super(name);
    }

    @Override
    public void show() {
        System.out.println(name);
    }
}
public class Folder extends Directory {

    private final List<Directory> dirs = new ArrayList<>();
    private final Integer level;

    public Folder(String name, int level) {
        super(name);
        this.level = level;
    }

    @Override
    public void show() {
        System.out.println(name);
        if (level == null) {
            return;
        }
        for (Directory dir : dirs) {
            for (int i = 0; i < level; i++) {
                dir.show();
            }
        }
    }

    public void addDir(Directory dir){
        dirs.add(dir);
    }

    public void removeDir(Directory dir){
        dirs.remove(dir);
    }

    public Directory getDir(int index){
        return dirs.get(index);
    }
}
```



对比透明模式和安全模式：

![](..\..\resources\image\composite_pattern_uml.jpg)

- 透明模式客户端不需要区分树枝和叶节点，违背接口隔离原则，叶子节点继承到不应继承的方法
- 安全模式可以获取到树枝节点的层级，违背依赖倒置原则，客户端调用时需区分树枝和叶节点
- 组合模式本质上是忽略层次的差异性以达到简化目的，透明模式在抽象根节点中忽略，安全模式在树节点中判断，二者没有优劣之分，仅是组合模式思想体现的不同形式。让我想起以前在《程序员的数学》中对于“0”的阐述，0即是无，也可以是一种特殊的有



#### 源码应用

JDK HashMap：

HashMap中的Node<K,V>[] tab 属性，其Node为一个节点

```java
transient Node<K,V>[] table;
```

开源框架Mybatis：

Mybatis解析各自Mapping文件中的SQL，设计了一个顶层接口SqlNode，xml中的每一个Node都会被解析为一个SqlNode对象，最后拼接成一条完整SQL

```java
public interface SqlNode {
  boolean apply(DynamicContext context);
}
```



------

### 个人心得

- 适配器模式不是在软件设计阶段所考虑的,随着软件的维护,由于各自原因造成的功能相似而接口不同的情况,是一种亡羊补牢的解决方案





