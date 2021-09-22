package com.hughes.demeter.v2;

import com.hughes.demeter.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hughes-T
 * @since 2021/9/22 14:23
 */
public class Employee {
    public void checkNum() {
        List<Product> products = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            products.add(new Product());
        }
        System.out.println("检测数量为:" + products.size());
    }
}
