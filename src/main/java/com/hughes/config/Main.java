package com.hughes.config;

/**
 * @author hughes-T
 * @since 2021/10/14 10:10
 */
public class Main {
    private String name;

    public Main(String name) {
        this.name = name;
    }

    public static void main(String[] args) {
      Object[] params = new Object[2];
      params[0] = new Main("张三");
      params[1] = new Main("李四");
      method1(params);
    }

    private static void method1(Object... args){
        System.out.println(args.length);
        for (Object arg : args) {
            System.out.println(arg);
        }
    }
}
