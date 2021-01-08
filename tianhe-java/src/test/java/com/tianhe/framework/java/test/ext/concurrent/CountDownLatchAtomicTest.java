package com.tianhe.framework.java.test.ext.concurrent;



import com.tianhe.framework.java.ext.concurrent.CountDownLatchAtomic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author: he.tian
 * @time: 2019-07-08 17:19
 */
public class CountDownLatchAtomicTest {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        CountDownLatchAtomic countDownLatch = new CountDownLatchAtomic(10);
        for (int i = 0; i < 10; i++) {
            executorService.execute(new Runnable() {
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
        System.out.println("所有子线程执行完毕");
        executorService.shutdown();
    }
}
