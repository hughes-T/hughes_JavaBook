## Spring mvc 源码



### 九大组件

- 加载入口

```java
DispatchServlet

	initServletBean

		initWebApplicationContext

			onRefresh 

				initStrategies 
```

- |      组件      |        作用         |
  | :------------: | :-----------------: |
  | HandlerMapping |  保存URL与方法映射  |
  | HandlerApapter |   动态参数适配器    |
  | ViewResolvers  | 视图转换器,模板引擎 |

- 

------
### 控制



------


### 注入



------


### 切面



------

### 个人心得






