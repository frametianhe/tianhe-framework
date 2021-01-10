package com.tianhe.writtentest.procon;

import java.util.LinkedList;
import java.util.concurrent.Semaphore;

/**
 * Created by tianhe on 2020/3/28.
 */
public class SemaphoreProCon {

    public static void main(String[] args) {

        LinkedList queue = new LinkedList();

        Semaphore lock = new Semaphore(1);
        Semaphore empty = new Semaphore(1);
        Semaphore full = new Semaphore(0);

        new Thread(){
            @Override
            public void run() {
                try {
                   for (;;){
                       empty.acquire();
                       lock.acquire();
                       System.out.println("生产了一个消息");
                       queue.addLast("a");
                       lock.release();
                       full.release();
                   }

                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }.start();

        new Thread(){
            @Override
            public void run() {
               try {
                   for (;;){
                       full.acquire();
                       lock.acquire();
                       System.out.println("消费了一个产品 "+queue.removeFirst());
                       lock.release();
                       empty.release();
                   }
               }catch (Exception e){
                   e.printStackTrace();
               }

            }
        }.start();
    }
}
