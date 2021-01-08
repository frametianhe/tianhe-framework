package com.tianhe.framework.java.test.ext.concurrent;

import com.tianhe.framework.java.ext.concurrent.CyclicCountDownLatch;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author jiangweifeng
 * @date 2020/04/27
 * @description
 */
public class CyclicCountDownLatchTest {

    public static void main(String[] args) throws InterruptedException {
        Random random = new Random();
        CyclicCountDownLatch cyclicCountDownLatch = new CyclicCountDownLatch(1);
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println(random.nextInt(100000000));
                cyclicCountDownLatch.countDown();
            }
        });
        cyclicCountDownLatch.await();
        System.out.println("======================================");
        cyclicCountDownLatch.reset(5);
        for (int i=0;i<5;i++){
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(random.nextInt(100000000));
                    cyclicCountDownLatch.countDown();
                }
            });
        }
        cyclicCountDownLatch.await();
        executorService.shutdown();
    }
}
