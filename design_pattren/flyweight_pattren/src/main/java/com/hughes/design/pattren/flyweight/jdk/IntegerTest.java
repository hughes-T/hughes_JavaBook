package com.hughes.design.pattren.flyweight.jdk;

/**
 * @author hughes-T
 * @since 2021/9/22 21:59
 */
public class IntegerTest {

    public static void main(String[] args) {
        Integer a = Integer.valueOf(100);
        Integer b = 100;
        Integer c = Integer.valueOf(128);
        Integer d = 128;
        System.out.println(a == b);
        System.out.println(c ==d);
    }
}
