## Spring快速模拟

### 前置

- 配置及分包参阅[源码](https://github.com/hughes-T/hughes_JavaBook/tree/dev/spring_source_code/spring_1.0/src/main/java/com/hughes/spring/source/v1)
- 核心类入口 [HDispatcherServlet](https://github.com/hughes-T/hughes_JavaBook/blob/dev/spring_source_code/spring_1.0/src/main/java/com/hughes/spring/source/v1/framework/servlet/HDispatcherServlet.java)

DispatcherServlet负责处理web端请求，本篇将其作为万能类快速体验spring 功能



------
### 解析

初始化 init（）

1. 加载配置文件 doLoadConfig（）
2. 扫描相关的类 doSacnner（）
3. 实例化类，加载至Ioc容器 doInstance（）
4. 依赖注入 doAutowied（）
5. 初始化 HandelrMapping （）

运行 doDispatch（）

​	由url匹配到对应的handelrMapping 触发反射

------

### 个人心得

在一个类里面快速体验了Spring的实现，这帮助我们理解了Spring的运行流程：读取配置->扫描->加载->依赖注入->缓存路径映射；
拓展思考，我们虽然完成了功能实现，但是DispatcherServlet作为委派类，为保证单一职责，后续将会一步步拆解出Ioc、Di、mvc等核心职责




