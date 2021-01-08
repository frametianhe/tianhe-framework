package com.tianhe.framework.java.study.threadswitch.cas;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * Created by tianhe on 2019/11/5.
 */
public class ThreadSwitchCasDemo {

    private volatile int threadSwitch = 0;

    private AtomicIntegerFieldUpdater<ThreadSwitchCasDemo> refThreadSwitchUpdater = AtomicIntegerFieldUpdater.newUpdater(ThreadSwitchCasDemo.class,"threadSwitch");

    public static void main(String[] args) {

    }

    private void service() {
        if(refThreadSwitchUpdater.compareAndSet(this,0,1)){
            System.out.println("有任务在执行,线程结束");
            return;
        }
        try {
            System.out.println("执行业务逻辑，确保当前业务逻辑只有一个线程执行");
        } finally {
            refThreadSwitchUpdater.compareAndSet(this,1,0);
        }
    }
}
