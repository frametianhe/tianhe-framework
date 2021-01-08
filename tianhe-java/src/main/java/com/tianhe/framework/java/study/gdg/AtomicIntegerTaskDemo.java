package com.tianhe.framework.java.study.gdg;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * atomic实现任务分发,线程同步
 * weifeng.jiang 2018-06-22 15:18
 */
public class AtomicIntegerTaskDemo {

    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger();
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i=0;i<10;i++){
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName());
                    atomicInteger.incrementAndGet();//模拟semaphore.release()
                }
            });
        }
//        这个for循环模拟semaphore.acquire(10)
// 可以实现一般不这么用，这里是自旋锁实现，如果并发调用这个业务方法，cpu会load高，如果子线程的任务执行的时间比较长，cpu load高的时间就会很长
        for (;atomicInteger.compareAndSet(10,0);){
            System.out.println("子线程都执行完毕");
        }
        executorService.shutdown();
    }
}
