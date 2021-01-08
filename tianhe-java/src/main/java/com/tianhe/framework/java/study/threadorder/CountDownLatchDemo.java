package com.tianhe.framework.java.study.threadorder;


import com.tianhe.framework.java.ext.concurrent.CountDownLatch;

/**
 * Created by tianhe on 2019/9/11.
 */
public class CountDownLatchDemo {

    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(1);

        Thread t1 = new Thread(){
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
                countDownLatch.countDown();
            }
        };

        Thread t2 = new Thread(){
            @Override
            public void run() {
                countDownLatch.await();
                System.out.println(Thread.currentThread().getName());
            }
        };
        t1.start();
        t2.start();
    }
}
