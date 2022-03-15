package com.hughes;

import com.hughes.one2many.IEnumOne2ManyDemo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
 * @Description
 * @Author hughesT
 * @Date 2022/3/15 19:47
 */
@SpringBootApplication

public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
        IEnumOne2ManyDemo.getInstance(IEnumOne2ManyDemo.Type.FIRST).method();
        IEnumOne2ManyDemo.getInstance(IEnumOne2ManyDemo.Type.TWO).method();
    }
}
