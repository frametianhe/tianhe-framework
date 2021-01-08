package com.tianhe.framework.java.study.gdg;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 用阻塞队列实现任务分发,线程同步
 * 只要是有线程同步的方法的数据结构都可以实现这种功能
 *
 * weifeng.jiang 2018-06-22 15:10
 */
public class BlockQueueTaskDemo {

    public static void main(String[] args) {
        BlockingQueue<Integer> queue = new LinkedBlockingQueue(10);
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        for (int i=0;i<10;i++){
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName());
                    try {
                        queue.put(1);//类似semaphore.release();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
//        这个循环的作用类似semaphore.acquire(10);
        for (int i=0;i<10;i++){
            try {
                queue.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        executorService.shutdown();
    }
}
