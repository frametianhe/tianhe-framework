package com.tianhe.framework.java.study.threadorder;

import java.util.concurrent.Semaphore;

/**
 * Created by tianhe on 2019/9/11.
 */
public class SemaphoreDemo {

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(0);

        Thread t1 = new Thread(){
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
                semaphore.release();
            }
        };

        Thread t2 = new Thread(){
            @Override
            public void run() {
                try {
                    semaphore.acquire();
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
