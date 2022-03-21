package com.hughes.concurrent.thread;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.*;

/*
 * @Description 线程创建测试
 * @Author hughesT
 * @Date 2022/3/17 9:12
 */
public class ThreadCreateTest {

    private Thread thread;

    @After
    public void after() throws Exception {
        if (thread != null && Thread.State.NEW.equals(thread.getState())){
            thread.start();
            thread.join();
        }
    }

    @Test
    public void inheritCreate(){
        thread = new MyThread();
    }

    @Test
    public void realizeCreate() {
        thread = new Thread(new MyRunnableImpl());
    }

    @Test
    public void futureTaskCreate() throws Exception{
        String expect = "ok";
        FutureTask<String> futureTask = new FutureTask<>(new MyCallAbleImpl<>(expect));//构造参即为返回值
        new Thread(futureTask).start();
        Assert.assertEquals(expect,futureTask.get());
    }

    @Test
    public void poolCreate() throws Exception {
        ExecutorService pool = Executors.newCachedThreadPool();
        String expect = "ok";
        Future<String> future = pool.submit(new MyCallAbleImpl<>(expect));//构造参即为返回值
        pool.execute(new MyRunnableImpl());
        pool.awaitTermination(100, TimeUnit.MILLISECONDS);
        Assert.assertEquals(expect,future.get());
    }


}
