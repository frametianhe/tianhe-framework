package com.tianhe.framework.java.test.ext.concurrent;

import com.tianhe.framework.java.ext.concurrent.LatchMutex;

import java.util.concurrent.TimeUnit;

public class LatchMutexTest {

    public static void main(String[] args) {
        LatchMutex latchMutex = new LatchMutex();
        new Thread() {
            public void run() {
                try {
                    latchMutex.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("我等会儿再执行");
            }
        }.start();

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("我执行完了");
        latchMutex.release();
    }
}
