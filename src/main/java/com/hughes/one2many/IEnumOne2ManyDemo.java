package com.hughes.one2many;

import com.hughes.util.ApplicationContextUtil;
import org.springframework.context.ApplicationContext;

import java.util.Map;

/*
 * @Description 接口枚举式一对多实现
 * @Author hughesT
 * @Date 2022/3/15 19:40
 */
public interface IEnumOne2ManyDemo {

    void method();

    Type getType();

    static IEnumOne2ManyDemo getInstance(Type type){
        ApplicationContext context = ApplicationContextUtil.getContext();
        Map<String, IEnumOne2ManyDemo> beans = context.getBeansOfType(IEnumOne2ManyDemo.class);
        for (Map.Entry<String, IEnumOne2ManyDemo> beanEntry : beans.entrySet()) {
            if (type.equals(beanEntry.getValue().getType())){
                return beanEntry.getValue();
            }
        }
        throw new RuntimeException("can not find "+ type.name() +" instance");
    }

    enum Type{
        FIRST,TWO,THREE;
    }

}
