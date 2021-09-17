package com.hughes.design.pattren.builder;

import lombok.Data;

/**
 * @author hughes-T
 * @since 2021/8/11 15:59
 */
public class PhoneBuilder {
    private final Phone phone = new Phone();

    public Phone build(){
        return phone;
    }

    public PhoneBuilder addBrand(String brand){
        phone.setBrand(brand);
        return this;
    }

    public PhoneBuilder addColor(String color){
        phone.setColor(color);
        return this;
    }

    public PhoneBuilder addPrice(double price){
        phone.setPrice(price);
        return this;
    }

    @Data
    public class Phone{
        private String brand;
        private String color;
        private double price;
    }
}
