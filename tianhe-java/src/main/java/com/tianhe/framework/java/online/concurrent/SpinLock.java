package com.tianhe.framework.java.online.concurrent;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 财务平台自旋锁实现，适合小并发场景，效率比较高
 * weifeng.jiang
 */
public class SpinLock {

    private AtomicBoolean spinLock = new AtomicBoolean(true);

    public void lock() {
        boolean flag;
        do {
            flag = this.spinLock.compareAndSet(true, false);
        }
        while (!flag);
    }


    public void unlock() {
        this.spinLock.compareAndSet(false, true);
    }
}
