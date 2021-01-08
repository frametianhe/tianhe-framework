package com.tianhe.framework.java.ext.concurrent;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * 扩展AbstractQueuedSynchronizer，用AtomicInteger实现一个CountDownLatch
 * @author: he.tian
 * @time: 2019-07-15 14:27
 */
public class CountDownLatchAtomic {

    private Sync sync;

    public CountDownLatchAtomic(int count){
        if(count < 0){
            throw new IllegalArgumentException();
        }
        sync = new Sync(count);
    }

    public void countDown(){
        sync.releaseShared(1);
    }

    public void await() throws InterruptedException{
        sync.acquireSharedInterruptibly(1);
    }

    public int getCount(){
        return sync.getCount();
    }

    private static final class Sync extends AbstractQueuedSynchronizer{

        private static final long serialVersionUID = 4982264981922014374L;

        private AtomicInteger count = new AtomicInteger();

        public Sync(int count){
            this.count.getAndAdd(count);
        }

        @Override
        protected boolean tryReleaseShared(int releases) {
            for (;;){
                if(count.get() == 0){
                    return false;
                }
                count.decrementAndGet();
                return true;
            }
        }

        @Override
        protected int tryAcquireShared(int acquires) {
           return (count.get() == 0) ? 1 : -1;
        }

        protected int getCount(){
            return count.get();
        }
    }
}
