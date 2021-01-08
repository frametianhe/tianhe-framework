package com.tianhe.framework.disruptor.study.procon;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 阻塞队列 实现一对一的生产者消费者模型
 * @author:weifeng.jiang
 * @DATE:2016年12月30日 @TIME: 下午1:03:00
 */
public class LinkedBlockingQueueProCon {

    public static void main(String[] args) {
        final BlockingQueue<LogEvent> queue = new LinkedBlockingQueue<LogEvent>();
        final long startTime = System.currentTimeMillis();
        
        //生产者
        new Thread(new Runnable() {

            @Override
            public void run() {
                int i = 0;
                while (i < 5000000) {
                    LogEvent logEvent = new LogEvent(i, "c" + i);
                    try {
                        queue.put(logEvent);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    i++;
                }
            }
        }).start();

        //消费者
        new Thread(new Runnable() {

            @Override
            public void run() {
                int k = 0;
                while (k < 5000000) {
                    try {
                        queue.take();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    k++;
                }
                long endTime = System.currentTimeMillis();
                System.out.println("costTime = " + (endTime - startTime) + "ms");
            }
        }).start();
    }
}