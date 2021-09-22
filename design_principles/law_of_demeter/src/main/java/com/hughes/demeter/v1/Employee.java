package com.hughes.demeter.v1;

import com.hughes.demeter.Product;

import java.util.List;

/**
 * @author hughes-T
 * @since 2021/9/22 14:17
 */
public class Employee {
    public void checkNum(List<Product> products) {
        System.out.println("检测数量为:" + products.size());
    }
}
