package com.tianhe.framework.java.test.ext.concurrent;


import com.tianhe.framework.java.ext.concurrent.Mutex;

public class Ticket implements Runnable {

    int num = 100;
    Mutex lock = new Mutex();

    @Override
    public void run() {
        //定义一个永真循环一直去卖票
        System.out.println(Thread.currentThread().getName() + "进程等待中");

        while (true)//有票就卖票
        {
            try {
                lock.lockInterruptibly();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (num > 0) {
                //卖票手续用了10ms
                try {
                    Thread.sleep(10);//因为其父类没有抛异常，这里只能trycatch处理
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "正在卖第" + num + "张票");
                num--;

            }
            lock.unlock();
        }
    }
}