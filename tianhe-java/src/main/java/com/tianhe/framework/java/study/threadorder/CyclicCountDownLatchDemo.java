package com.tianhe.framework.java.study.threadorder;


import com.tianhe.framework.java.ext.concurrent.CyclicCountDownLatch;

/**
 * Created by tianhe on 2019/9/11.
 */
public class CyclicCountDownLatchDemo {

    public static void main(String[] args) {

        CyclicCountDownLatch cyclicCountDownLatch = new CyclicCountDownLatch(1);

        Thread t1 = new Thread() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
                cyclicCountDownLatch.countDown();
            }
        };

        try {
            cyclicCountDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        cyclicCountDownLatch.reset(1);
        Thread t2 = new Thread() {
            @Override
            public void run() {
                cyclicCountDownLatch.countDown();
                System.out.println(Thread.currentThread().getName());
            }
        };

        try {
            cyclicCountDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        t1.start();
        t2.start();
    }
}
