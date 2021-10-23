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
### 初始化阶段

HandlerMapping 保存Url映射关系
	url method control
HandlerApapter 动态参数适配器
	map<HandlerMaping,HandlerApater>
ViewResolvers 视图转换器,模板引擎
	List<ViewResolvers>

### 运行阶段

​	根据URL 拿到对应的Handler,拿到HandlerAdapter
​	拿到ModelAndView
​	根据ViewResolver找到对应view对象
​	渲染页面并返回



------

### 个人心得

暂无




