package com.hughes.concurrent.thread;

/*
 * @Description 继承
 * @Author hughesT
 * @Date 2022/3/17 8:42
 */

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MyThread extends Thread {

    @Override
    public void run() {
        log.info("MyThread is run...");
    }
}
