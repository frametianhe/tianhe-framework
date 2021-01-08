package com.tianhe.framework.java.ext.concurrent;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 如何实现一个CountDownLatch
 * @author: he.tian
 * @time: 2019-07-08 17:27
 */
public class CountDownLatch {

    private int count;

    private Lock lock = new ReentrantLock();

    private Condition condition = lock.newCondition();

    public CountDownLatch(int count){
        if(count < 0){
            throw new IllegalArgumentException();
        }
        this.count = count;
    }

    public void countDown(){
        try {
            lock.lock();
            --count;
//            最后一个线程执行完--count后主线程还在阻塞
            condition.signal();
        }finally {
            lock.unlock();
        }
    }

    public void await(){
        try {
            lock.lock();
            while (count != 0){
                try {
//                   设置线程同步点
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }finally {
            lock.unlock();
        }
    }
}
