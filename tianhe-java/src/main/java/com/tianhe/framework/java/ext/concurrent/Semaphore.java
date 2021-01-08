package com.tianhe.framework.java.ext.concurrent;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 如何实现一个Semaphore
 * @author: he.tian
 * @time: 2019-07-08 17:49
 */
public class Semaphore {

    private int count;

    private Lock lock = new ReentrantLock();

    private Condition condition = lock.newCondition();

    public Semaphore(int count){
        if(count < 0){
            throw new IllegalArgumentException();
        }
        this.count = count;
    }

    public void release(int count){
        lock.lock();
        try {
            this.count += count;
            condition.signal();
        }finally {
            lock.unlock();
        }
    }

    public void release(){
        release(1);
    }

    public void acquire(int count){
        lock.lock();
        try {
            while (this.count < count){
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }finally {
            lock.unlock();
        }
    }

    public void acquire(){
        acquire(1);
    }

}
