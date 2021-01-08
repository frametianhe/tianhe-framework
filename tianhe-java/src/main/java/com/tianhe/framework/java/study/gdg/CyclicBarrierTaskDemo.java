package com.tianhe.framework.java.study.gdg;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * CyclicBarrier实现任务分发,线程同步
 * weifeng.jiang 2018-06-22 10:50
 */
public class CyclicBarrierTaskDemo {

    public static void main(String[] args) throws Exception{
//        CyclicBarrier的parties、ExecutorService的nThreads、for循环要执行的任务数要一致，有一个线程在执行完成的时候会唤醒其他所有线程，如果不一致会导致最快执行的线程漏掉唤醒或不会唤醒的线程存在
//        这样就导致程序不会结束
        int proccessors = Runtime.getRuntime().availableProcessors();
        final CyclicBarrier cyclicBarrier = new CyclicBarrier(proccessors);
        ExecutorService executorService = Executors.newFixedThreadPool(proccessors);
        for (int i=0;i<proccessors;i++){
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName());
                    try {
                        cyclicBarrier.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        executorService.shutdown();
    }
}
