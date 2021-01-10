package com.tianhe.writtentest.procon;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by tianhe on 2020/3/28.
 */
public class LockPro {

    public static void main(String[] args) {

        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        LinkedList queue = new LinkedList();

        new Thread(){
            @Override
            public void run() {
                while (true){
                   try {
                       lock.lock();
                       while (!queue.isEmpty()){
                           try {
                               condition.await();
                           } catch (InterruptedException e) {
                               e.printStackTrace();
                           }
                       }
                       for (int i=0;i<10;i++){
                           System.out.println("生产了a");
                           queue.addLast("a");
                       }
                       condition.signal();
                   }finally {
                       lock.unlock();
                   }
                }
            }
        }.start();

        new Thread(){
            @Override
            public void run() {
                while (true){
                   try {
                       lock.lock();
                       while (queue.isEmpty()){
                           try {
                               condition.await();
                           } catch (InterruptedException e) {
                               e.printStackTrace();
                           }
                       }
                       for (int i=0;i<10;i++){
                           System.out.println("消费了 "+queue.removeFirst());
                       }
                       condition.signal();
                   }finally {
                       lock.unlock();
                   }
                }
            }
        }.start();
    }
}
