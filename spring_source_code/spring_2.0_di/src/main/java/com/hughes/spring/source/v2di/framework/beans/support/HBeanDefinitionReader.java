package com.hughes.spring.source.v2di.framework.beans.support;

import com.hughes.spring.source.v2di.framework.beans.config.HBeanDefinition;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * 配置解析器
 * @author hughes-T
 * @since 2021/10/6 23:03
 */
public class HBeanDefinitionReader {

    private final Properties contextConfig = new Properties();

    private final List<String> classNames = new ArrayList<String>();


    public HBeanDefinitionReader(String... configs) {
        doLoadConfig(configs[0]);
        doScanner(contextConfig.getProperty("scanPackage"));
    }

    public List<HBeanDefinition> loadBeanDefinitions(){
        List<HBeanDefinition> result = new ArrayList<HBeanDefinition>();
        for (String className : classNames) {
            try {
                Class<?> clazz = Class.forName(className);
                if (clazz.isInterface()){continue;}
                //默认首字母大小写
                String beanName = toLowerFirstCase(clazz.getSimpleName());
//                String beanName = clazz.getName();
                //有接口的情况

                for (Class<?> anInterface : clazz.getInterfaces()) {
                    beanName = toLowerFirstCase(anInterface.getSimpleName());
//                    beanName = anInterface.getName();
                }
                result.add(new HBeanDefinition(beanName,className));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return result;
    }

    private void doScanner(String scanPackage) {
        URL url = this.getClass().getClassLoader().getResource("/" + scanPackage.replaceAll("\\.","/"));
        File classPath = new File(url.getFile());
        for (File file : classPath.listFiles()) {
            if (file.isDirectory()) {
                doScanner(scanPackage + "." + file.getName());
            } else {
                if (!file.getName().endsWith(".class")) {
                    continue;
                }
                String className = scanPackage + "." + file.getName().replace(".class", "");
                classNames.add(className);
            }
        }
    }

    private void doLoadConfig(String contextConfigLocation) {
        InputStream fis = this.getClass().getClassLoader().getResourceAsStream(contextConfigLocation);
        try {
            contextConfig.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    private String toLowerFirstCase(String simpleName) {
        char [] chars = simpleName.toCharArray();
        //大小写字母的ASCII码相差32，
        chars[0] += 32;
        return String.valueOf(chars);
    }

}
