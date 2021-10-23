## SpringDi 源码解读

[源码参阅](/src/main/java/com/hughes/spring/source/v2di/framework/beans/support/HDefaultListableBeanFactory.java)

### 前置

在Spring ioc版本中,不能够解决bean循环注入的问题，通过二级缓存 + 创建mark标记解决这一问题

- singletonsCurrentlyInCreation 创建Mark
- singlentonObjects 一级缓存 , 成熟bean
- earlyObjects 二级缓存, 早期bean
- factoryBeanCache 三级缓存 , 用于AOP

### 流程解析

1. getBean（A），调用getSingleon 从一级缓存获取不到 ，mark标记，A实例化，存入二级缓存
2. A 依赖 B 调用 getBean（B），getSinglenton中获取不到，mark标记， B实例化，存入二级缓存
3. B依赖A再次调用getBean（A），getSinglenton 一级缓存获取不到但是存在mark标记，从二级缓存获取，完成注入

------

### 个人心得

spring di 三级缓存解决循环依赖的注入问题，并没有太多神秘的地方，不过它是面试的高频论点，能够考验出应聘者的逻辑能力和追求细致的态度。




