package com.hughes.spring.source.v2mvc.framework.servlet;

import com.hughes.spring.source.v2mvc.framework.annotation.HController;
import com.hughes.spring.source.v2mvc.framework.annotation.HRequestMapping;
import com.hughes.spring.source.v2mvc.framework.annotation.HRequestParam;
import com.hughes.spring.source.v2mvc.framework.context.HApplicationContext;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
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

public class HDispatcherServlet extends HttpServlet {


    /**
     * 持有IOC容器上下文的访问
     */
    private HApplicationContext applicationContext ;

    private final List<HuHandlerMapping> handlerMappings = new ArrayList<>();

    private final Map<HuHandlerMapping,HuHandlerAdapter> handlerAdapters = new HashMap<>();

    private final  List<HViewResolver> viewResolvers = new ArrayList<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //6、根据URL委派给具体的调用方法
        try {
            doDispatch(req,resp);
        } catch (Exception e) {
            e.printStackTrace();
//            resp.getWriter().write("500 Exception,Detail: " + Arrays.toString(e.getStackTrace()));
            Map<String,Object> model = new HashMap<String, Object>();
            model.put("detail","500 Exception,Detail: ");
            model.put("stackTrace",Arrays.toString(e.getStackTrace()));
            try {
                processDispatchResult(req,resp,new HModelAndView("500",model));
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

    /**
     * 委派
     * @author hughes-T 2021/9/30 15:36
     */
    private void doDispatch(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //1、根据URL 拿到对应的Handler
        HuHandlerMapping handler = getHandler(req);

        if(null == handler){
            processDispatchResult(req,resp,new HModelAndView("404"));
            return;
        }

        //2、根据HandlerMapping拿到HandlerAdapter
        HuHandlerAdapter ha = getHandlerAdapter(handler);

        //3、根据HandlerAdapter拿到对应的ModelAndView
        HModelAndView mv = ha.handle(req,resp,handler);

        //4、根据ViewResolver找到对应View对象
        //通过View对象渲染页面，并返回
        processDispatchResult(req,resp,mv);
    }

    private HuHandlerAdapter getHandlerAdapter(HuHandlerMapping handler) {
        if (this.handlerAdapters.isEmpty()){return null;}
        return handlerAdapters.get(handler);
    }

    private HuHandlerMapping getHandler(HttpServletRequest req) {
        String url = req.getRequestURI();
        String contextPath = req.getContextPath();
        url = url.replaceAll(contextPath,"").replaceAll("/+","/");

        for (HuHandlerMapping handlerMapping : this.handlerMappings) {
            Matcher matcher = handlerMapping.getPattern().matcher(url);
            if(!matcher.matches()){ continue;}
            return handlerMapping;
        }

        return null;
    }

    private void processDispatchResult(HttpServletRequest req, HttpServletResponse resp, HModelAndView mv) throws Exception {
        if(null == mv){return;}
        if(this.viewResolvers.isEmpty()){return;}

        for (HViewResolver viewResolver : this.viewResolvers) {
            HView view = viewResolver.resolveViewName(mv.getViewName());
            view.render(mv.getModel(),req,resp);
            return;
        }
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

        //========== 加载 MVC 九大组件  ==========
        initStrategies();

        System.out.println("GP Spring framework is init.");

    }

    private void initStrategies() {

        //url、method关系映射
        initHandlerMappings();

        //动态参数适配器
        initHandlerAdapter();

        //视图转换器，模板引擎
        initViewResolvers();

    }


    private void initHandlerMappings() {
        if(this.applicationContext.getBeanDefinitionCount() == 0 ){ return; }

        for (String beanName : this.applicationContext.getBeanDefinitionNames()) {
            Object instance = applicationContext.getBean(beanName);
            Class<?> clazz = instance.getClass();

            if(!clazz.isAnnotationPresent(HController.class)){ continue; }


            String baseUrl = "";
            if(clazz.isAnnotationPresent(HRequestMapping.class)){
                HRequestMapping requestMapping = clazz.getAnnotation(HRequestMapping.class);
                baseUrl = requestMapping.value();
            }

            //只迭代public方法
            for (Method method : clazz.getMethods()) {
                if(!method.isAnnotationPresent(HRequestMapping.class)){ continue; }

                HRequestMapping requestMapping = method.getAnnotation(HRequestMapping.class);
                //  //demo//query
                String regex = ("/" + baseUrl + "/" + requestMapping.value())
                        .replaceAll("\\*",".*")
                        .replaceAll("/+","/");
                Pattern pattern = Pattern.compile(regex);

                handlerMappings.add(new HuHandlerMapping(instance,method,pattern));
                System.out.println("Mapped : " + regex + " --> " + method);

            }
        }
    }


    private void initHandlerAdapter() {
        for (HuHandlerMapping handlerMapping : handlerMappings) {
            this.handlerAdapters.put(handlerMapping,new HuHandlerAdapter());
        }

    }

    private void initViewResolvers() {
        String templateRoot = this.applicationContext.getConfig().getProperty("templateRoot");
        String templateRootPath = this.getClass().getClassLoader().getResource(templateRoot).getFile();

        File fileDir = new File(templateRootPath);
        for (File file : fileDir.listFiles()) {
            this.viewResolvers.add(new HViewResolver(templateRoot));
        }
    }

}
