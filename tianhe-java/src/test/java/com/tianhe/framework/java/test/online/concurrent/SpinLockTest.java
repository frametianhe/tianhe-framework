package com.tianhe.framework.java.test.online.concurrent;


import com.tianhe.framework.java.online.concurrent.SpinLock;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by weifeng.jiang on 2017-10-13 16:20.
 */
public class SpinLockTest {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        CountDownLatch countDownLatch = new CountDownLatch(10);
       for (int i=0;i<10;i++){
           final int num = i;
           executorService.execute(new Runnable() {
               @Override
               public void run() {
                    print(num);
               }
           });
           countDownLatch.countDown();
       }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executorService.shutdown();
    }

    private static void print(int num){
        SpinLock lock = new SpinLock();
        lock.lock();
        System.out.println("测试"+num);
        lock.unlock();
    }
}
