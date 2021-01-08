package com.tianhe.framework.java.ext.concurrent;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 如何用linkedList实现一个LinkedBlockQueue
 * @author: he.tian
 * @time: 2019-07-11 17:51
 */
public class LinkedBlockingQueue<T> {

    private LinkedList<T> linkedList = new LinkedList();

    private Lock lock = new ReentrantLock();

    private Condition condition = lock.newCondition();

    public T take() {
        lock.lock();
        T t = linkedList.pollLast();
        try {
            if (t == null) {
                try {
                    condition.await();
                    t = linkedList.pollLast();
                    condition.signal();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                condition.signal();
            }
        } finally {
            lock.unlock();
        }
        return t;
    }

    public void put(T t) {
        lock.lock();
        try {
            if (linkedList.size() > 0) {
                try {
                    condition.await();
                    linkedList.push(t);
                    condition.signal();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                linkedList.push(t);
                condition.signal();
            }
        } finally {
            lock.unlock();
        }
    }
}
