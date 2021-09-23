package com.hughes.design.pattren.flyweight.jdk;

/**
 * 玩转String
 * @author hughes-T
 * @since 2021/9/22 17:17
 */
public class StringTest {
    public static void main(String[] args) {
        String s1 = "hello";
        String s2 = "hello";
        String s3 = "hel" + "lo";
        String s4 = "hel";
        String s5 = "lo";
        String s6 = s4 + s5;
        final String s7 = "hel";
        final String s8 = "lo";
        String s9 = s7 + s8;
        String s10 = new String("hello");
        String s11 = s10.intern();
        System.out.println(s1 == s2);
        System.out.println(s1 == s3);
        System.out.println(s1 == s6);
        System.out.println(s1 == s9);
        System.out.println(s1 == s10);
        System.out.println(s1 == s11);
    }

}
