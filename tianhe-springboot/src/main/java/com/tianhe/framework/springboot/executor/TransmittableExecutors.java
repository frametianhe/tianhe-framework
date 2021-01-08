package com.tianhe.framework.springboot.executor;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.stream.Collectors;

import lombok.extern.slf4j.Slf4j;

/**
 * 能传递上下文的线程服务
 *
 * @author humin
 */
@Slf4j
public class TransmittableExecutors implements ExecutorService {
    private final Map<String, Object> map;

    private final ExecutorService executorService;

    public TransmittableExecutors(Map<String, Object> map, ExecutorService executorService) {
        this.map = map;
        this.executorService = executorService;
    }

    /**
     * 包装执行器方法
     *
     * @param executorService 未经包装的执行器
     * @return 可传递上下文的执行器
     */
    public static TransmittableExecutors get(ExecutorService executorService) {
        final Map<String, Object> map = new HashMap<>(20);
        map.putAll(ContextUtil.CONTEXT.get());
        return new TransmittableExecutors(map, executorService);
    }

    /**
     * 获取包装过的 runnable
     *
     * @param runnable 未包装的 Runnable
     * @return 包装过的 Runnable
     */
    public Runnable get(Runnable runnable) {
        return () -> {
            ContextUtil.CONTEXT.set(map);
            try {
                runnable.run();
            } catch (Exception e) {
                log.error("多线程执行异常", e);
            }
        };
    }

    /**
     * 获取包装过的 callable
     *
     * @param callable 未包装的 callable
     * @param <T>      callable 的返回类型
     * @return 包装过的 callable
     */
    public <T> Callable<T> get(Callable<T> callable) {
        return () -> {
            ContextUtil.CONTEXT.set(map);
            try {
                return callable.call();
            } catch (Exception e) {
                log.error("多线程执行异常", e);
                return null;
            }
        };
    }

    @Override
    public void execute(Runnable runnable) {
        executorService.execute(get(runnable));
    }

    @Override
    public Future<?> submit(Runnable runnable) {
        return executorService.submit(get(runnable));
    }

    @Override
    public <T> Future<T> submit(Callable<T> task) {
        return executorService.submit(get(task));
    }

    @Override
    public <T> Future<T> submit(Runnable task, T result) {
        return executorService.submit(get(task), result);
    }

    @Override
    public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks)
            throws InterruptedException {
        return executorService.invokeAll(tasks.stream().map(this::get).collect(Collectors.toList()));
    }

    @Override
    public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit)
            throws InterruptedException {
        return executorService.invokeAll(tasks.stream().map(this::get).collect(Collectors.toList()), timeout, unit);
    }

    @Override
    public <T> T invokeAny(Collection<? extends Callable<T>> tasks)
            throws InterruptedException, ExecutionException {
        return executorService.invokeAny(tasks.stream().map(this::get).collect(Collectors.toList()));
    }

    @Override
    public <T> T invokeAny(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit)
            throws InterruptedException, ExecutionException, TimeoutException {
        return executorService.invokeAny(tasks.stream().map(this::get).collect(Collectors.toList()), timeout, unit);
    }

    @Override
    public void shutdown() {
        executorService.shutdown();
    }

    @Override
    public List<Runnable> shutdownNow() {
        return executorService.shutdownNow();
    }

    @Override
    public boolean isShutdown() {
        return executorService.isShutdown();
    }

    @Override
    public boolean isTerminated() {
        return executorService.isTerminated();
    }

    @Override
    public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
        return executorService.awaitTermination(timeout, unit);
    }

}
