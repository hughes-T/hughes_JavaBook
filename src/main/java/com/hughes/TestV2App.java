package com.hughes;

import com.alibaba.fastjson.JSONObject;
import com.hughes.one2many.v1.IEnumOne2ManyDemo;
import com.hughes.one2many.v2.IEnumOne2ManyV2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
 * @Description
 * @Author hughesT
 * @Date 2022/3/15 19:47
 */
@SpringBootApplication
public class TestV2App {
    public static void main(String[] args) {
        SpringApplication.run(TestV2App.class, args);
        IEnumOne2ManyV2 instance = IEnumOne2ManyV2.getInstance(IEnumOne2ManyV2.Type.FIRST);
        JSONObject param = new JSONObject();
        param.put("firstReqName","hhh");
        System.out.println(instance.save(param));
        Object o = instance.querySelective(new JSONObject());
    }
}
