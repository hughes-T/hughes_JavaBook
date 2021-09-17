package com.hughes.design.pattren.prototype;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author hughes-T
 * @since 2021/8/11 14:46
 */
public class Test {
    public static void main(String[] args) throws Exception {
        List<String> hobbies = new ArrayList<>();
        hobbies.add("读书");
        hobbies.add("跑步");
        PrototypeObject prototypeObject = new PrototypeObject().setName("张三").setAge(18).setHobbies(hobbies);

        PrototypeObject cloneObj = JSON.parseObject(JSON.toJSONString(prototypeObject),prototypeObject.getClass());
        cloneObj.getHobbies().clear();
        System.out.println(prototypeObject == cloneObj);
        System.out.println(prototypeObject);
        System.out.println(cloneObj);
    }

    private static void method() throws IOException, ClassNotFoundException {
        List<String> hobbies = new ArrayList<>();
        hobbies.add("读书");
        hobbies.add("跑步");
        PrototypeObject prototypeObject = new PrototypeObject().setName("张三").setAge(18).setHobbies(hobbies);
//        PrototypeObject cloneObj = prototypeObject.clone();
//        cloneObj.getHobbies().clear();//原型hobbies也被清空
//        System.out.println(prototypeObject);
//        System.out.println(cloneObj);
//        System.out.println(prototypeObject == cloneObj);//false
        PrototypeObject cloneObj = (PrototypeObject) DeepCloneUtil.deepClone(prototypeObject);
        cloneObj.getHobbies().clear();
        System.out.println(prototypeObject == cloneObj);
        System.out.println(prototypeObject);
        System.out.println(cloneObj);
    }
}
