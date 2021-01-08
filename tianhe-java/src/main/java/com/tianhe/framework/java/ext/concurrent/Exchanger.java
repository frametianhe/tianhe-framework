package com.tianhe.framework.java.ext.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 如何实现一个Exchanger
 * @author: he.tian
 * @time: 2019-07-09 17:24
 */
public class Exchanger<T> {

    private List<T> list = new ArrayList<>();

    private Lock lock = new ReentrantLock();

    private Condition condition = lock.newCondition();

    public T exchange(T t){
        lock.lock();
       try {
           if(list.size() == 0){
               list.add(t);
               try {
                   condition.await();
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
               T result = list.get(0);
               list.clear();
               return result;
           }else{
               T result = list.get(0);
               list.clear();
               list.add(t);
               condition.signal();
               return result;
           }
       }finally {
           lock.unlock();
       }
    }
}
