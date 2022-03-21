package com.hughes.concurrent.thread;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

/*
 * @Description 线程方法测试
 * @Author hughesT
 * @Date 2022/3/17 10:36
 */
@Slf4j
public class ThreadMethodTest {

    @Test
    public void testInterrupt() throws InterruptedException {
        Thread t1 = new Thread(()->{
            log.info("t1 is run...");
            Thread t = Thread.currentThread();
            try {
                t.interrupt();
                log.info("t1 线程中断状态：" + t.isInterrupted());//true
                TimeUnit.SECONDS.sleep(1); //InterruptedException: sleep interrupted
            } catch (Exception e) {
                e.printStackTrace();
                log.info("异常 t1 线程中断状态：" + t.isInterrupted());//false
                try {
                    TimeUnit.SECONDS.sleep(1);
                    log.info("异常后sleep成功");
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
            log.info("t1 is end...");
        },"t1线程");
        t1.start();
        t1.join();
    }
}
