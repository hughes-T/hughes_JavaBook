package com.hughes.concurrent.thread;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

/*
 * @Description 线程状态测试
 * @Author hughesT
 * @Date 2022/3/16 14:13
 */
@Slf4j
public class ThreadStateTest {

    /**
     * Blocked 状态情形一：线程阻塞等待监视器锁的线程状态（Thread state for a thread blocked waiting for a monitor lock.）
     */
    @Test
    public void testBlocked01() throws Exception {
        byte[] lock = new byte[0];
        Thread t1 = new Thread(() -> {
            synchronized (lock) {
                log.info("t1 has get lock...");
                for (int i = 0; i < 100; i++) {
                    try {
                        TimeUnit.SECONDS.sleep(3);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                log.info("t1 is end");
            }
        }, "t1线程");
        Thread t2 = new Thread(() -> {
            log.info("t2 is start");
            synchronized (lock) {
            log.info("t2 is end");
            }
        }, "t2线程");
        t1.start();
        TimeUnit.MICROSECONDS.sleep(100);//保证t1已启动
        t2.start();
        TimeUnit.SECONDS.sleep(200);//给visualVM足够时间浏览
        Assert.assertEquals(Thread.State.BLOCKED,t2.getState());
    }

    /**
     * Blocked 状态情形二：处于阻塞状态的线程正在等待监视器锁进入同步块/方法或在调用Object.wait后重新进入同步块/方法
     * （ A thread in the blocked state is waiting for a monitor lock to enter a synchronized block/method
     *  or reenter a synchronized block/method after calling）
     */
    @Test
    public void testBlocked02() throws Exception {
        byte[] lock2 = new byte[0];
        Thread t1 = new Thread(()->{
            try {
                synchronized (lock2){
                    log.info("step1： t1获取到lock,t2将在wait前 blocked 30s");
                    TimeUnit.SECONDS.sleep(30);//t2 BLOCKED waiting to lock lockPath
                    log.info("step2： t1释放lock...");
                }
                TimeUnit.MILLISECONDS.sleep(100);//保证t2能够获取到资源
                synchronized (lock2){
                    log.info("step4: t1再次获取lock,将在30s后notify t2");
                    TimeUnit.SECONDS.sleep(30);//t2 WAITING waiting on lock , locked lockPath
                    lock2.notify();
                    log.info("step5: t2被notify,状态由wait切换至blocked,t1将在60s后释放lock");
                    TimeUnit.MINUTES.sleep(1);//t2 BLOCKED waiting on lock , locked lockPath
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"t1线程");

        Thread t2 = new Thread(() -> {
            try {
                synchronized (lock2){
                    log.info("step3： t2 获取到lock,将wait并释放lock");
                    lock2.wait();
                    log.info("step6: t2 is end");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"t2线程");
        t1.start();
        TimeUnit.MILLISECONDS.sleep(100);//保证t1能够获取到资源
        t2.start();
        TimeUnit.MINUTES.sleep(3);
    }


}
