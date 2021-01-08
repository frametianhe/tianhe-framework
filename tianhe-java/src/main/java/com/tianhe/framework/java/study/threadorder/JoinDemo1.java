package com.tianhe.framework.java.study.threadorder;

/**
 * Created by tianhe on 2019/9/11.
 */
public class JoinDemo1 {

    public static void main(String[] args) {

        Thread t1 = new Thread(){
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        };

        Thread t2 = new Thread(){
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        };

        try {
            t1.join();
            t1.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            t2.join();
            t2.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
