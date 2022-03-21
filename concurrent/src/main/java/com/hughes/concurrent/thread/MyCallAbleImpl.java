package com.hughes.concurrent.thread;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;

/*
 * @Description 实现Callable接口
 * @Author hughesT
 * @Date 2022/3/17 8:46
 */
@Slf4j
public class MyCallAbleImpl<V> implements Callable<V> {

    private V expect;

    public MyCallAbleImpl(V expect){
        this.expect = expect;
    }

    @Override
    public V call() throws Exception {
        log.info("MyCallAbleImpl is run...");
        return expect;
    }

}
