package com.tianhe.writtentest.procon;

import java.util.LinkedList;

/**
 * Created by tianhe on 2020/3/28.
 */
public class WaitPro {

    public static void main(String[] args) {

        LinkedList queue = new LinkedList();

        new Thread() {
            @Override
            public void run() {

                for (; ; ) {
                    synchronized (queue) {
                        while (!queue.isEmpty()) {
                            try {
                                queue.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }

                        for (int i = 0; i < 10; i++) {
                            System.out.println("生产了a");
                            queue.addLast("a");
                        }
                        queue.notify();

                    }
                }
            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                for (; ; ) {
                    synchronized (queue) {
                        while (queue.isEmpty()) {
                            try {
                                queue.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        for (int i = 0; i < 10; i++) {
                            System.out.println("消费了 "+queue.removeFirst());
                        }
                        queue.notify();

                    }
                }
            }
        }.start();
    }
}
