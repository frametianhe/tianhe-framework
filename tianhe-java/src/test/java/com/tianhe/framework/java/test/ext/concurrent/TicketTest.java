package com.tianhe.framework.java.test.ext.concurrent;

public class TicketTest {
    public static void main(String[] args) {
        //创建runnable接口类实现对象
        Ticket t=new Ticket();
        //创建三个线程实现卖票任务并启动
        new Thread(t).start();
        new Thread(t).start();
        new Thread(t).start();

//    如果多个线程操作共享数据，那么有可能引发线程安全问题。本类是出现线程安全的例子
//打印结果：会出现多个窗口卖出同一张票。
    }

}