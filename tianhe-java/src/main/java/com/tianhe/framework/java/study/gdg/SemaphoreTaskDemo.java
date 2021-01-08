package com.tianhe.framework.java.study.gdg;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * semaphore实现线程分发,线程同步
 * weifeng.jiang 2018-06-21 21:04
 */
public class SemaphoreTaskDemo {

    public static void main(String[] args) throws Exception{
        Semaphore semaphore = new Semaphore(0);
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        for (int i=0;i<10;i++){
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName());
                    semaphore.release();
                }
            });
        }
        semaphore.acquire(10);
        executorService.shutdown();
    }
}
