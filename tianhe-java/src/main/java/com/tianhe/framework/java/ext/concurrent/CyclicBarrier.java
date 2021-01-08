package com.tianhe.framework.java.ext.concurrent;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 怎么实现一个CyclicBarrier
 * @author: he.tian
 * @time: 2019-07-08 16:28
 */
public class CyclicBarrier {

    private int count;

    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public CyclicBarrier(int count){
        if(count < 0){
            throw new IllegalArgumentException();
        }
        this.count = count;
    }

    public void await(){
        try {
            lock.lock();
//            同步维护一个count计数器的值
            if(--count == 0){
//                线程之间相互唤醒
                condition.signalAll();
            }
            try {
//                如果当前count计数器的值是0说明当前线程是最后一个执行完成任务的线程不需要await
                if(count != 0){
                    condition.await();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } finally {
            lock.unlock();
        }
    }
}
