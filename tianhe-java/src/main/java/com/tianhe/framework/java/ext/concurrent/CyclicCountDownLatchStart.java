package com.tianhe.framework.java.ext.concurrent;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * 计数器，扩展了重置计数器功能
 * @author: he.tian
 * @time: 2018-08-22 15:26
 */
public class CyclicCountDownLatchStart {

    private final Sync sync;

    public CyclicCountDownLatchStart(int count){
        if(count < 0){
            throw new RuntimeException("计数器初始值不能小于0");
        }
        this.sync = new Sync(count);
    }

    /**
     * 当前线程等待计数器值等于0，之前一直处于休眠转状态，如果其他线程中断当前线程会抛出InterruptedException
     * @throws InterruptedException
     */
    public void await() throws InterruptedException{
        sync.acquireSharedInterruptibly(1);
    }

    /**
     * 当前线程等待计数器值等于0，之前一直处于休眠转状态，如果其他线程中断当前线程会抛出InterruptedException，如果超过指定的等待时间返回false
     * @param timeout
     * @param unit
     * @return
     * @throws InterruptedException
     */
    public boolean await(long timeout, TimeUnit unit) throws InterruptedException{
        return sync.tryAcquireSharedNanos(1,unit.toNanos(timeout));
    }

    /**
     * 计数器值减1，直到等于0释放所有等待线程
     */
    public void countDown(){
        sync.releaseShared(1);
    }

    /**
     * 返回计数器的当前值
     * @return
     */
    public long getCount(){
        return sync.getCount();
    }

    /**
     * 重置计数器的值
     */
    public void reset(){
        sync.reset();
    }

    public String toString() {
        return "[count = "+sync.getCount()+"]";
    }

    /**
     * cas同步器
     */
    private static final class Sync extends AbstractQueuedSynchronizer{

        //初始计数器值
        private final int startCount;

        Sync(int count){
            this.startCount = count;
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

        protected void reset(){
            setState(startCount);
        }
    }
}
