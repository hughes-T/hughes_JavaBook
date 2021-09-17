package com.hughes.design.pattren.builder;

/**
 * @author hughes-T
 * @since 2021/8/11 16:03
 */
public class Test {
    public static void main(String[] args) {
        PhoneBuilder phoneBuilder = new PhoneBuilder()
                .addBrand("小米")
                .addColor("黑色")
                .addPrice(1999.00);
        System.out.println(phoneBuilder.build());
    }
}
