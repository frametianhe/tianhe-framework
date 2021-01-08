package com.tianhe.framework.java.study.threadswitch.atomic;

import lombok.Getter;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by tianhe on 2019/11/5.
 */
public class ThreadSwitchAtomicBooleanDemo {

    @Getter
    private AtomicBoolean threadSwitch = new AtomicBoolean();

    public static void main(String[] args) {

        new ThreadSwitchAtomicBooleanDemo().service();
    }

    private void service() {
        if(getThreadSwitch().compareAndSet(false,true)){
            System.out.println("有任务在执行,线程结束");
            return;
        }
        try {
            System.out.println("执行业务逻辑，确保当前业务逻辑只有一个线程执行");
        } finally {
            getThreadSwitch().compareAndSet(true,false);
        }
    }
}
