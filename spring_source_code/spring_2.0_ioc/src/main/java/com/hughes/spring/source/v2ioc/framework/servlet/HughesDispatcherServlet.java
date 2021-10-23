package com.hughes.spring.source.v2ioc.framework.servlet;

import com.hughes.spring.source.v2ioc.framework.annotation.*;
import com.hughes.spring.source.v2ioc.framework.context.HApplicationContext;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author hughes-T
 * @since 2021/9/28 0:02
 */

public class HughesDispatcherServlet extends HttpServlet {


    /**
     * 持有IOC容器上下文的访问
     */
    private HApplicationContext applicationContext ;

    private final List<Handler> handlers = new ArrayList<Handler>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //委派handlers
        try {
            doDispatch(req,resp);
        } catch (Exception e) {
            e.printStackTrace();
            resp.getWriter().write("500 Exection,Detail : " + Arrays.toString(e.getStackTrace()));
        }
    }

    /**
     * 委派
     * @author hughes-T 2021/9/30 15:36
     */
    private void doDispatch(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        Handler handler = getHandler(req);
        if(handler == null){
            resp.getWriter().write("404 Not Found!!!");
            return;
        }
        //方法的参数表列表
        Class<?>[] paramTypes = handler.method.getParameterTypes();
        Object[] paramValues = new Object[ paramTypes.length];
        Map<String,String[]> params = req.getParameterMap();
        for (Map.Entry<String, String[]> parm : params.entrySet()) {
            String value = Arrays.toString(parm.getValue()).replaceAll("\\[|\\]","")
                    .replaceAll("\\s",",");

            if(!handler.paramIndexMapping.containsKey(parm.getKey())){continue;}

            int index = handler.paramIndexMapping.get(parm.getKey());
            paramValues[index] = convert(paramTypes[index],value);
        }

        if(handler.paramIndexMapping.containsKey(HttpServletRequest.class.getName())) {
            int reqIndex = handler.paramIndexMapping.get(HttpServletRequest.class.getName());
            paramValues[reqIndex] = req;
        }

        if(handler.paramIndexMapping.containsKey(HttpServletResponse.class.getName())) {
            int respIndex = handler.paramIndexMapping.get(HttpServletResponse.class.getName());
            paramValues[respIndex] = resp;
        }

        Object returnValue = handler.method.invoke(handler.controller,paramValues);
        if(returnValue == null || returnValue instanceof Void){ return; }
        resp.getWriter().write(returnValue.toString());
    }

    //url传过来的参数都是String类型的，HTTP是基于字符串协议
    //只需要把String转换为任意类型就好
    private Object convert(Class<?> type,String value){
        //如果是int
        if(Integer.class == type){
            return Integer.valueOf(value);
        }
        else if(Double.class == type){
            return Double.valueOf(value);
        }
        //如果还有double或者其他类型，继续加if
        //这时候，我们应该想到策略模式了
        //在这里暂时不实现，希望小伙伴自己来实现
        return value;
    }

    private Handler getHandler(HttpServletRequest req) {
        if (handlers.isEmpty()){return null;}
        String url = req.getRequestURI();
        String contextPath = req.getContextPath();
        url = url.replaceAll(contextPath,"").replaceAll("/+","/");

        for (Handler handler : handlers) {
            Matcher matcher = handler.pattern.matcher(url);
            if (matcher.matches()){
                return handler;
            }
        }
        return null;
    }

    @Override
    public void init(ServletConfig config) throws ServletException {

//        //1、加载配置文件
//        doLoadConfig(config.getInitParameter("contextConfigLocation"));
//
//        //2、扫描相关的类
//        doScanner(contextConfig.getProperty("scanPackage"));
//
//        //3、实例化扫描到的类，加载至 IOC 容器
//        doInstance();
//
//        //4、依赖注入
//        doAutowired();

        applicationContext = new HApplicationContext(config.getInitParameter("contextConfigLocation"));

        //5、初始化HandlerMapping
        initHandlerMapping();

        System.out.println("hughes Spring framework v1 is init.");
    }


    /**
     * 初始化url和method的映射关系
     *
     * @author hughes-T 2021/9/30 13:44
     */
    private void initHandlerMapping() {
        if (applicationContext.getBeanDefinitionCount() == 0){return;}
        for (String beanName : applicationContext.getBeanDefinitionNames()) {
            Object instance = applicationContext.getBean(beanName);
            Class<?> clazz = instance.getClass();
            if (!clazz.isAnnotationPresent(GPController.class)){continue;}

            String baseUrl = "";
            if (clazz.isAnnotationPresent(GPRequestMapping.class)){
                baseUrl = clazz.getAnnotation(GPRequestMapping.class).value();
            }
            //获取所有public方法
            for (Method method : clazz.getMethods()) {
                if (!method.isAnnotationPresent(GPRequestMapping.class)){continue;}
                GPRequestMapping requestMapping = method.getAnnotation(GPRequestMapping.class);
//优化
                // //demo///query
                String regex = ("/" + baseUrl + "/" + requestMapping.value())
                        .replaceAll("/+","/");
                Pattern pattern = Pattern.compile(regex);
                this.handlers.add(new Handler(pattern,instance,method));
                System.out.println("Mapped:"+ pattern + "-->" + method);

            }




        }
    }



    /**
     * 封装 Method 和 url 关系
     * @author hughes-T 2021/9/30 15:06
     */
    private class Handler{

        private Method method;
        private Object controller;
        private Pattern pattern;

        //形参列表
        //参数的名字作为key,参数的顺序，位置作为值
        private Map<String,Integer> paramIndexMapping;

        public Handler(Pattern pattern,Object controller, Method method){
            this.pattern = pattern;
            this.method = method;
            this.controller = controller;

            paramIndexMapping = new HashMap<String, Integer>();
            putParamIndexMapping(method);
        }


        /**
         * 提取方法上注解的参数以及位置信息
         * @author hughes-T 2021/9/30 15:14
         */
        private void putParamIndexMapping(Method method) {
            //方法有多个参数，参数有多个注解
            //第一个维度为参数，第二个维度为注解
            Annotation[][] an2s = method.getParameterAnnotations();

            for (int i = 0; i < an2s.length; i++) {
                for (Annotation an : an2s[i]) {
                    if (an instanceof GPRequestParam){
                        String paramName = ((GPRequestParam) an).value();
                        if (!"".equals(paramName)){
                            paramIndexMapping.put(paramName,i);
                        }
                    }
                }
            }

            //提取方法中的request和response参数
            Class<?> [] paramsTypes = method.getParameterTypes();
            for (int i = 0; i < paramsTypes.length ; i ++) {
                Class<?> type = paramsTypes[i];
                if(type == HttpServletRequest.class ||
                        type == HttpServletResponse.class){
                    paramIndexMapping.put(type.getName(),i);
                }
            }

        }

    }

}
