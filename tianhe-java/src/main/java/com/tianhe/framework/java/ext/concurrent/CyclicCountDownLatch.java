package com.tianhe.framework.java.ext.concurrent;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

public class CyclicCountDownLatch {

    private final Sync sync;

    public CyclicCountDownLatch(int count){
        if(count < 0){
            throw new RuntimeException("计数器初始值不能小于0");
        }
        this.sync = new Sync(count);
    }

    /**
     * @throws InterruptedException
     */
    public void await() throws InterruptedException{
        sync.acquireSharedInterruptibly(1);
    }

    /**
     * @param timeout
     * @param unit
     * @return
     * @throws InterruptedException
     */
    public boolean await(long timeout, TimeUnit unit) throws InterruptedException{
        return sync.tryAcquireSharedNanos(1,unit.toNanos(timeout));
    }

    public void countDown(){
        sync.releaseShared(1);
    }

    public long getCount(){
        return sync.getCount();
    }

    public void reset(int count){
        sync.reset(count);
    }

    public String toString() {
        return "[count = "+sync.getCount()+"]";
    }

    private static final class Sync extends AbstractQueuedSynchronizer{


        Sync(int count){
            setState(count);
        }

        int getCount(){
            return getState();
        }

        protected int tryAcquireShared(int acquires){
            return (getState() == 0) ? 1 : -1;
        }

        protected boolean tryReleaseShared(int releases){
            for (;;){
                int count = getState();
                if(count == 0){
                    return false;
                }
                int nextCount = count-1;
                if(compareAndSetState(count,nextCount)){
                    return nextCount == 0;
                }
            }
        }

        protected void reset(int count){
            setState(count);
        }
    }
}
