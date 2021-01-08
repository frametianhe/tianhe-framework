package com.tianhe.framework.java.test.ext.concurrent;

import com.tianhe.framework.java.ext.concurrent.CyclicCountDownLatch;
import com.tianhe.framework.java.ext.concurrent.CyclicCountDownLatchStart;
import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author: he.tian
 * @time: 2018-08-22 15:47
 */
public class CyclicCountDownLatchStartTest {

    @Test
    public void testCounter(){
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        final CyclicCountDownLatchStart cyclicCountDownLatch = new CyclicCountDownLatchStart(20);
        for (int i=0;i<20;i++){
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName());
                    cyclicCountDownLatch.countDown();
                }
            });
        }
        try {
            cyclicCountDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executorService.shutdown();
    }

    @Test
    public void testCounterReset(){
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        final CyclicCountDownLatchStart cyclicCountDownLatch = new CyclicCountDownLatchStart(20);
        for (int i=0;i<20;i++){
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName());
                    cyclicCountDownLatch.countDown();
                }
            });
        }
        try {
            cyclicCountDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        cyclicCountDownLatch.reset();

        System.out.println("==================================");

        for (int i=0;i<20;i++){
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName());
                    cyclicCountDownLatch.countDown();
                }
            });
        }
        try {
            cyclicCountDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        executorService.shutdown();
    }
}
