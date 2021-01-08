package com.tianhe.framework.java.study.procon;

import java.util.concurrent.*;

/**
 * @author: he.tian
 * @time: 2019-06-12 16:14
 */
public class ArrayBlockQueueDemo {

    public static void main(String[] args) {

        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue(100);
        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                try {
                    for (int i=0;i<10;i++){
                        blockingQueue.put("a");
                        System.err.println(Thread.currentThread().getName()+"存储了a元素");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },2,5, TimeUnit.SECONDS);

        new Thread(){
            @Override
            public void run() {
                String take = null;
                try {
                   while (true){
                       take = blockingQueue.take();
                       System.err.println(Thread.currentThread().getName()+"获取了"+take+"元素");
                   }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}
