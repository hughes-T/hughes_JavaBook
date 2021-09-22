package com.hughes.demeter.v1;

import com.hughes.demeter.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hughes-T
 * @since 2021/9/22 14:17
 */
public class Leader {

    public void commandCheckNum(Employee employee){
        List<Product> products = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            products.add(new Product());
        }
        employee.checkNum(products);
    }
}
