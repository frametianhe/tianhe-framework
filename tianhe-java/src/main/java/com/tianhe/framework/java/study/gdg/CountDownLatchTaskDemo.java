package com.tianhe.framework.java.study.gdg;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * countDownLatch实现任务分发,线程同步
 * 线程同步工具只要有同步方法就可以实现任务分发，等待所有子任务执行完毕后主线程再往下执行
 * weifeng.jiang 2018-06-21 20:32
 */
public class CountDownLatchTaskDemo {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        CountDownLatch countDownLatch = new CountDownLatch(10);
        for (int i=0;i<10;i++){
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName());
                    countDownLatch.countDown();
                }
            });
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executorService.shutdown();
    }
}
