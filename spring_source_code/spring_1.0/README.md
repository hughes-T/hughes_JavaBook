## Spring快速模拟

### 前置

- 配置及分包参阅[源码]()
- 核心类入口 HDispatcherServlet

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






