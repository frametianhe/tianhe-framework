package com.tianhe.framework.java.ext.concurrent;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * 基于AbstractQueuedSynchronizer实现一个同步开关，类似于CountDownLatch计数器为1的情况
 *
 * @author: he.tian
 * @time: 2019-07-15 12:47
 */
public class LatchMutex {

    private Sync sync;

    public LatchMutex() {
        sync = new Sync();
        sync.setStates(1);
    }

    /**
     * 获取不到锁会阻塞
     * @throws InterruptedException
     */
    public void acquire() throws InterruptedException {
        sync.acquire();
    }

    public void release() {
        sync.release();
    }

    private final class Sync extends AbstractQueuedSynchronizer {

        private static final long serialVersionUID = 2559471934544126329L;

        @Override
        protected int tryAcquireShared(int state) {
            return getState() == 0 ? 1 : -1;
        }

        @Override
        protected boolean tryReleaseShared(int state) {
            return true;
        }

        public void setStates(int state) {
            setState(state);
        }

        protected void acquire() throws InterruptedException {
            acquireSharedInterruptibly(0);
        }

        protected void release() {
            for (; ; ) {
                if(getState() == 0){
                    return;
                }
                if (compareAndSetState(1, 0)) {
                    releaseShared(0);
                    return;
                }
            }
        }
    }
}
