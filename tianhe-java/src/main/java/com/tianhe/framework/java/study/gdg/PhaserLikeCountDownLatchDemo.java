package com.tianhe.framework.java.study.gdg;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Phaser;

/**
 * Phaser类机制是在每一步结束的位置对线程进行同步，当所有的线程都完成了这一步，才能进行下一步。
 当我们有并发任务并且需要分解成几步执行的时候，这种机制就非常适合。
 cyclicBarrier countDownLatch 只能在构造时指定参与量，而phaser可以动态的增减参与量
 phaser类一个重大的特性就是不必对他的方法进行异常处理。

 实现一个countDownLatch任务分发,线程同步
 * weifeng.jiang 2018-06-22 14:20
 */
public class PhaserLikeCountDownLatchDemo {

    public static void main(String[] args) {
        final Phaser phaser = new Phaser(10);
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i=0;i<10;i++){
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName());
                    phaser.arriveAndDeregister();//取消注册，类似于countDownLatch.countDown()
                }
            });
        }
        phaser.awaitAdvance(phaser.getPhase()); //类似于countDownLatch.await()
        executorService.shutdown();
    }
}
