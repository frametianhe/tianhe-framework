package com.tianhe.framework.springboot.executor;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

import lombok.extern.slf4j.Slf4j;

/**
 * 能带入上下文信息的线程工厂
 *
 * @author humin@didichuxing.com
 * @since 2017/7/25
 */
@Slf4j
public class NamedThreadFactory implements ThreadFactory {

    private final ThreadGroup group;

    private final AtomicInteger threadNumber = new AtomicInteger(1);

    private final String namePrefix;

    private final boolean daemon;

    private final Integer priority;

    private final Thread.UncaughtExceptionHandler exceptionHandler;

    /**
     * @param name 名称
     */
    public NamedThreadFactory(String name) {
        SecurityManager securityManager = System.getSecurityManager();
        group = (securityManager != null) ? securityManager.getThreadGroup() : Thread.currentThread().getThreadGroup();
        namePrefix = name;
        daemon = false;
        priority = Thread.NORM_PRIORITY;
        exceptionHandler = (thread, exception) -> log.error(thread.getName(), exception);
    }

    /**
     * @param group            组
     * @param namePrefix       名称前缀
     * @param daemon           是否守护线程
     * @param priority         优先级
     * @param exceptionHandler 异常处理器
     */
    public NamedThreadFactory(ThreadGroup group, String namePrefix, Boolean daemon, Integer priority,
            Thread.UncaughtExceptionHandler exceptionHandler) {
        this.group = group;
        this.namePrefix = namePrefix;
        this.daemon = daemon;
        this.priority = priority;
        this.exceptionHandler = exceptionHandler;
    }

    /**
     * 创建新线程的方法
     *
     * @param runnable 可执行任务
     * @return 线程
     */
    @Override
    public Thread newThread(Runnable runnable) {
        String name = namePrefix + threadNumber.getAndIncrement() + "-thread-";
        Thread thread = new Thread(group, runnable, name, 0);
        thread.setDaemon(daemon);
        thread.setPriority(priority);
        thread.setUncaughtExceptionHandler(exceptionHandler);
        return thread;
    }
}
