package com.tianhe.framework.java.ext.concurrent;

/**
 * Created by tianhe on 2019/9/11.
 */
public class CyclicCountDownLatchLock {

    public static void main(String[] args) {
        CyclicCountDownLatch cyclicCountDownLatch = new CyclicCountDownLatch(1);

        for (int i = 0; i < 10; i++) {
            new Thread() {
                @Override
                public void run() {
                    cyclicCountDownLatch.countDown();
                    System.err.println(Thread.currentThread().getName());
                    cyclicCountDownLatch.reset(1);
                }
            }.start();
        }
    }
}
