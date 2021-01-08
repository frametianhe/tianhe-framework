package com.tianhe.framework.java.study.concurrent;

import java.util.concurrent.Semaphore;

/**
 * @author: he.tian
 * @time: 2019-04-29 11:06
 */
public class SemaphoreLockDemo {

    public static void main(String[] args) {

//        信号量的信号是1的时候可以作为互斥锁来使用
        Semaphore semaphore = new Semaphore(1);

        for (int i=0;i<10;i++){
            new Thread(){
                @Override
                public void run() {
                    try {
                        semaphore.acquire();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.err.println(Thread.currentThread().getName());
                        semaphore.release();
                }
            }.start();
        }
    }
}
