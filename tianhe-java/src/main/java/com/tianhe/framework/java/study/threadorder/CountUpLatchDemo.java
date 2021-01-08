package com.tianhe.framework.java.study.threadorder;


import com.tianhe.framework.java.ext.concurrent.CountUpLatch;

/**
 * Created by tianhe on 2019/9/11.
 */
public class CountUpLatchDemo {

    public static void main(String[] args) {
        CountUpLatch countUpLatch = new CountUpLatch();

        Thread t1 = new Thread(){
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
                countUpLatch.countUp();
            }
        };

        Thread t2 = new Thread(){
            @Override
            public void run() {
                try {
                    countUpLatch.await(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName());
            }
        };
        t1.start();
        t2.start();
    }
}
