package com.hughes.design.pattern.proxy.domain;

/**
 * @author hughes-T
 * @since 2021/8/15 12:40
 */
public class SimpleConsumer implements IConsumer{
    @Override
    public void eatFood() {
        System.out.println("吃");
    }
}
