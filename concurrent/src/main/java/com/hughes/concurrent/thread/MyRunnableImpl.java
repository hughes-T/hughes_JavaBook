package com.hughes.concurrent.thread;

import lombok.extern.slf4j.Slf4j;

/*
 * @Description 实现Runnable接口
 * @Author hughesT
 * @Date 2022/3/17 8:45
 */
@Slf4j
public class MyRunnableImpl implements Runnable {
    @Override
    public void run() {
      log.info("MyRunnableImpl is run...");
    }
}
