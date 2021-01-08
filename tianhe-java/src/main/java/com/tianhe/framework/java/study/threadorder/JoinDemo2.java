package com.tianhe.framework.java.study.threadorder;

/**
 * Created by tianhe on 2019/9/11.
 */
public class JoinDemo2 {

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
                try {
                    t1.join();
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
