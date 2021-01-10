package com.tianhe.writtentest.procon;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by tianhe on 2020/3/28.
 */
public class ConPro {

    public static void main(String[] args) {
        Lock lock  = new ReentrantLock();
        Condition putCondition = lock.newCondition();
        Condition takeCondition = lock.newCondition();
        LinkedList queue = new LinkedList();

        new Thread(){
            @Override
            public void run() {
                while (true){
                    try {
                        lock.lock();
                        while (!queue.isEmpty()){
                            try {
                                putCondition.await();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        for (int i=0;i<10;i++){
                            queue.addLast("a"+i);
                            System.out.println("生产了a"+i);
                        }
                        takeCondition.signal();
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
                                takeCondition.await();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        for (int i=0;i<10;i++){
                            System.out.println("消费了 "+queue.removeFirst());
                        }
                        putCondition.signal();
                    }finally {
                        lock.unlock();
                    }
                }
            }
        }.start();
    }

}
