package com.tianhe.framework.java.study.gdg;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Phaser;

/**
 实现一个cyclicBarrier完成任务分发,线程同步
 * weifeng.jiang 2018-06-22 14:20
 */
public class PhaserLikeCyclicBarrierDemo {

    public static void main(String[] args) {
        final Phaser phaser = new Phaser(10);
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i=0;i<10;i++){
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName()+"   第一步");
                    phaser.arriveAndAwaitAdvance();//完成第一步，等待其他线程完成第一步，类似于cyclicBarrier.await()
                    System.out.println(Thread.currentThread().getName()+"   第二步");
                    phaser.arriveAndAwaitAdvance();
                }
            });
        }
        executorService.shutdown();
    }
}
