package com.hughes.desginPrinciples.dependenceInversion.v3;

import org.springframework.stereotype.Service;

/**
 * @author hughes-T
 * @since 2021/9/18 22:25
 */
@Service
public class DiscernTaskHandler implements ITaskHandlerWithEnum {
    @Override
    public TaskHandlerType getType() {
        return TaskHandlerType.DISCERN;
    }

    @Override
    public void handler() {
        System.out.println("处理检测型任务");
    }
}
