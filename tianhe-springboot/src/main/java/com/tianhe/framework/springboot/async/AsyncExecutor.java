package com.tianhe.framework.springboot.async;

import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executor;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Component
public class AsyncExecutor implements AsyncConfigurer {

    @Override
    public Executor getAsyncExecutor() {
        return new ThreadPoolExecutor(0, 5,
                60L, TimeUnit.SECONDS,
                new SynchronousQueue<Runnable>(), new NamedThreadFactory("async", 1, false));
    }
}