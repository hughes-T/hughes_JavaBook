package com.hughes.one2many.v2;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hughes.util.ApplicationContextUtil;
import com.hughes.util.ClassUtil;
import org.springframework.context.ApplicationContext;

import java.util.Map;

/*
 * @Description 枚举式一对多,增加泛型消除参数差异
 * @Author hughesT
 * @Date 2022/6/6 16:35
 */
public interface IEnumOne2ManyV2<T,V> {

    Type getType();

    int save(JSONObject reqParam);

    V querySelective(JSONObject po);

    default T convertParam(JSONObject reqParam){
        return JSON.parseObject(reqParam.toJSONString(), ClassUtil.getActualTypes(this.getClass())[0]);
    }

    default V convertResult (JSONObject result){
        return JSON.parseObject(result.toJSONString(), ClassUtil.getActualTypes(this.getClass())[1]);
    }

    @SuppressWarnings("rawtypes")
    static IEnumOne2ManyV2 getInstance(Type type){
        ApplicationContext context = ApplicationContextUtil.getContext();
        Map<String, IEnumOne2ManyV2> beans = context.getBeansOfType(IEnumOne2ManyV2.class);
        for (Map.Entry<String, IEnumOne2ManyV2> beanEntry : beans.entrySet()) {
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
