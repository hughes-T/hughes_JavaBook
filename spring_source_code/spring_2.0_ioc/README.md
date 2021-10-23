## Spring Ioc

基于spring 快速体验的版本，开发spring ioc

源码参阅:https://github.com/hughes-T/hughes_JavaBook/tree/dev/spring_source_code/spring_2.0_ioc

------
### 顶层设计

- BeanFactory Bean工厂，缓存Bean元信息
- ApplicationContext 对用户开放，门面模式，持有BeanFactory引用，隔离创建细节
- BeanDefintionReader 配置解析器
- BeanDefintion 配置元信息
- BeanWrapper bean包装器

------

### 解析

- DispatcherServlet 持有ApplicationContext 将除了handlerMapping（在spring mvc版本优化）以外的行为解耦
- ApplicationContext 初始化时加载BeanFactory
- BeanFactory 缓存BeanDefintionReader 解析的BeanDefintion，并执行实例化和依赖注入
- BeanFactory的getBean方法实现创建bean 的细节



------

### 个人心得

简单聊一下我对于Spring接口设计的看法，结合之前的spring快速模拟的版本，当时在委派类中，将每个阶段的动作分为一个方法，不过委派类的职责不应如此，因此站在面向对象的编程思想中，spring将每个大小动作拆解为不同的对象，“行为型”的对象入BeanFactory负责创建Bean、Reader负责解析配置，然后它们之间在进行协作时将信息集封装为实体入BeanDefintion、BeanWrapper，最终完成各自的职责，结构和逻辑如此清晰，可谓编码之魅力。

在初入j2EE开发时，发现很多能够懂得东西在运用之中总是陷入大脑空白，无奈之中写下“垃圾山”。读懂一个知识体系往往还不能去灵活应用，古人云“横看成岭侧成峰”，如果仅仅只是读懂spring的运行逻辑，我们创造一个“万能类”并不代表我们掌握了spring，站在设计思想的角度去体会spring设计背后的原因，才会帮助我们在实际开发之中打开思路。


