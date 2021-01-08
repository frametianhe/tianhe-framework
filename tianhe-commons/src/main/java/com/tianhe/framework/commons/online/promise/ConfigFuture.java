package com.tianhe.framework.commons.online.promise;

import lombok.Setter;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 *  类似于java的CompleteFuture
 * Created by tianhe on 2019/9/29.
 */
public class ConfigFuture {

    @Setter
    private Object data;

    private static final long TIMEOUT = 5 * 1000;

    private final CountDownLatch latch = new CountDownLatch(1);

    public Object get(long timeout, TimeUnit timeUnit){
        try {
            latch.await(timeout,timeUnit);
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        }
        return data;
    }

    public Object get(){
        return get(TIMEOUT,TimeUnit.MILLISECONDS);
    }

    public void setData(Object data){
        this.data = data;
        latch.countDown();
    }
}
