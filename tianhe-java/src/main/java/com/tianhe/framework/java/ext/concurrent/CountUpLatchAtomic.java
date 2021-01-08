package com.tianhe.framework.java.ext.concurrent;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * 扩展AbstractQueuedSynchronizer，用AtomicInteger实现一个CountUpLatch
 * @author: he.tian
 * @time: 2019-07-15 14:27
 */
public class CountUpLatchAtomic {

    private Sync sync;

    public CountUpLatchAtomic(){
        sync = new Sync();
    }

    public void countUp(){
        sync.releaseShared(1);
    }

    public void await(int acquires) throws InterruptedException{
        sync.acquireSharedInterruptibly(acquires);
    }

    public int getCount(){
        return sync.getCount();
    }

    private static final class Sync extends AbstractQueuedSynchronizer{

        private static final long serialVersionUID = 4982264981922014374L;

        private AtomicInteger count = new AtomicInteger();

        @Override
        protected boolean tryReleaseShared(int releases) {
            count.incrementAndGet();
            return true;
        }

        @Override
        protected int tryAcquireShared(int acquires) {
           return (count.get() == acquires) ? 1 : -1;
        }

        protected int getCount(){
            return count.get();
        }
    }
}
