package com.tianhe.framework.java.ext.concurrent;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * 基于AbstractQueuedSynchronizer扩展累计计数器
 * @author: he.tian
 * @time: 2019-07-16 10:39
 */
public class CountUpLatch {

    private static final class Sync extends AbstractQueuedSynchronizer{

        private static final long serialVersionUID = 4982264981922014374L;

        Sync(int count){
            setState(count);
        }

        int getCount(){
            return getState();
        }

        @Override
        protected int tryAcquireShared(int acquires) {
            return (getState() == acquires) ? 1 : -1;
        }

        @Override
        protected boolean tryReleaseShared(int releases) {
            for (;;){
                int c = getState();
                int nextc = getState()+1;
                if(compareAndSetState(c,nextc)){
                    return true;
                }
            }
        }
    }

    private final Sync sync;

    public CountUpLatch(){
        this.sync = new Sync(0);
    }

    public void await(int count) throws InterruptedException{
        sync.acquireSharedInterruptibly(count);
    }

    public void countUp(){
        sync.releaseShared(1);
    }

    public long getCount(){
        return sync.getCount();
    }
}
