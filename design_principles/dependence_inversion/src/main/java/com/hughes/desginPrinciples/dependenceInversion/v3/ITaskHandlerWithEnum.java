package com.hughes.desginPrinciples.dependenceInversion.v3;

import org.springframework.context.ApplicationContext;

import java.util.Map;

/*
 * @Description 任务调度器枚举式实现
 * @Author hughesT
 * @Date 2022/4/19 10:24
 */
public interface ITaskHandlerWithEnum {

    TaskHandlerType getType();

    void handler();

    static ITaskHandlerWithEnum getInstance(TaskHandlerType type){
        ApplicationContext context = ApplicationContextUtil.getContext();
        Map<String, ITaskHandlerWithEnum> beans = context.getBeansOfType(ITaskHandlerWithEnum.class);
        for (Map.Entry<String, ITaskHandlerWithEnum> entry : beans.entrySet()) {
            if (type.equals(entry.getValue().getType())){
                return entry.getValue();
            }
        }
        throw new RuntimeException(String.format("can not find %s instance", type.name()));
    }

    enum TaskHandlerType{
        BEHAVIOR,DISCERN
    }

}
