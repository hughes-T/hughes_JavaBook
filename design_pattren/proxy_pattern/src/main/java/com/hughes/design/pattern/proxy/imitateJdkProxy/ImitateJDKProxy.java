package com.hughes.design.pattern.proxy.imitateJdkProxy;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * 模拟 JDK 代理
 *
 * @author hughes-T
 * @since 2021/8/26 15:35
 */
@SuppressWarnings("rawtypes")
public class ImitateJDKProxy {


    /**
     * 返回指定接口的代理类实例
     *
     * @param classLoader 类加载器
     * @param interfaces  代理类接口列表
     * @param h 代理类方法的处理
     */
    @SuppressWarnings({"unchecked"})
    public static Object newProxyInstance(ImitateJDKClassLoader classLoader,
                                          Class<?>[] interfaces,
                                          ImitateJDKInvocationHandler h) throws Exception {
        //动态生成 java源文件
        String src = generateSrc(interfaces);
        String filePath = ImitateJDKProxy.class.getResource("").getPath();
        //存储文件
        File file = new File(filePath + "$ImitateJDKProxy0.java");
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write(src);
        fileWriter.flush();
        fileWriter.close();
        //编译文件
        JavaCompiler javaCompiler = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager standardFileManager = javaCompiler.getStandardFileManager(null, null, null);
        Iterable<? extends JavaFileObject> iterable = standardFileManager.getJavaFileObjects(file);
        JavaCompiler.CompilationTask task = javaCompiler.getTask(null,standardFileManager,null,null,null,iterable);
        task.call();
        standardFileManager.close();
        //file.delete();
        //加载到jvm中
        Class proxyClass = classLoader.findClass("$ImitateJDKProxy0");
        Constructor constructor = proxyClass.getConstructor(ImitateJDKInvocationHandler.class);
        //创建对象
        return constructor.newInstance(h);

    }
    private final static String ln = System.getProperty("line.separator");

    /**
     * 生成java源文件
     */
    private static String generateSrc(Class<?>[] interfaces){
        StringBuilder sb = new StringBuilder();
        sb.append(ImitateJDKProxy.class.getPackage()).append(";").append(ln);
        sb.append("import ").append(interfaces[0].getName()).append(";").append(ln);
        sb.append("import java.lang.reflect.*;").append(ln);
        sb.append("public class $ImitateJDKProxy0 implements ").append(interfaces[0].getSimpleName()).append("{").append(ln);
        sb.append("ImitateJDKInvocationHandler h;").append(ln);
        sb.append("public $ImitateJDKProxy0(ImitateJDKInvocationHandler h) { ").append(ln);
        sb.append("this.h = h;");
        sb.append("}").append(ln);
        for (Method m : interfaces[0].getMethods()){
            Class<?>[] params = m.getParameterTypes();

            StringBuilder paramNames = new StringBuilder();
            StringBuilder paramValues = new StringBuilder();
            StringBuilder paramClasses = new StringBuilder();

            for (int i = 0; i < params.length; i++) {
                Class clazz = params[i];
                String type = clazz.getName();
                String paramName = toLowerFirstCase(clazz.getSimpleName());
                paramNames.append(type).append(" ").append(paramName);
                paramValues.append(paramName);
                paramClasses.append(clazz.getName()).append(".class");
                if(i > 0 && i < params.length-1){
                    paramNames.append(",");
                    paramClasses.append(",");
                    paramValues.append(",");
                }
            }

            sb.append("public ").append(m.getReturnType().getName()).append(" ").append(m.getName()).append("(").append(paramNames.toString()).append(") {").append(ln);
            sb.append("try{").append(ln);
            sb.append("Method m = ").append(interfaces[0].getName()).append(".class.getMethod(\"").append(m.getName()).append("\",new Class[]{").append(paramClasses.toString()).append("});").append(ln);
            sb.append(hasReturnValue(m.getReturnType()) ? "return " : "").append(getCaseCode("this.h.invoke(this,m,new Object[]{" + paramValues + "})", m.getReturnType())).append(";").append(ln);
            sb.append("}catch(Error _ex) { }");
            sb.append("catch(Throwable e){").append(ln);
            sb.append("throw new UndeclaredThrowableException(e);").append(ln);
            sb.append("}").append(ln);
            sb.append(getReturnEmptyCode(m.getReturnType()));
            sb.append("}").append(ln);
        }
        sb.append("}").append(ln);
        return sb.toString();
    }


    private static final Map<Class,Class> mappings = new HashMap<>();
    static {
        mappings.put(int.class,Integer.class);
    }

    private static String getReturnEmptyCode(Class<?> returnClass){
        if(mappings.containsKey(returnClass)){
            return "return 0;";
        }else if(returnClass == void.class){
            return "";
        }else {
            return "return null;";
        }
    }

    private static String getCaseCode(String code,Class<?> returnClass){
        if(mappings.containsKey(returnClass)){
            return "((" + mappings.get(returnClass).getName() +  ")" + code + ")." + returnClass.getSimpleName() + "Value()";
        }
        return code;
    }

    private static boolean hasReturnValue(Class<?> clazz){
        return clazz != void.class;
    }

    private static String toLowerFirstCase(String src){
        char [] chars = src.toCharArray();
        chars[0] += 32;
        return String.valueOf(chars);
    }

}
