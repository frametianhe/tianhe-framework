package com.tianhe.framework.java.ext.concurrent;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * 基于AbstractQueuedSynchronizer实现一个互斥锁,类似于Semaphore，信号量为1的情况
 *
 * @author: he.tian
 * @time: 2019-07-15 12:47
 */
public class Mutex {

    private Sync sync;

    public Mutex() {
        sync = new Sync();
        sync.setStates(0);
    }

    public void lockInterruptibly() throws InterruptedException {
        sync.lockInterruptibly();
    }

    public void unlock() {
        sync.unlock();
    }

    private final class Sync extends AbstractQueuedSynchronizer {

        private static final long serialVersionUID = 2559471934544126329L;

        @Override
        protected int tryAcquireShared(int state) {
            int c = getState() == 0 ? 1 : -1;
            for (;;){
                compareAndSetState(0,1);
                return c;
            }
        }

        @Override
        protected boolean tryReleaseShared(int state) {
            return true;
        }

        public void setStates(int state) {
            setState(state);
        }

        protected void lockInterruptibly() throws InterruptedException {
            acquireSharedInterruptibly(0);
        }

        protected void unlock() {
            for (; ; ) {
                if (compareAndSetState(1, 0)) {
                    releaseShared(0);
                    return;
                }
            }
        }
    }
}
