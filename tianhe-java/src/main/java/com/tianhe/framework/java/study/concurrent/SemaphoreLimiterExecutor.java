package com.tianhe.framework.java.study.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 使用Semaphore实现限流
 * @author jiangweifeng
 *
 */
public class SemaphoreLimiterExecutor {

	private Semaphore limitSemaphore;

	private Semaphore syncSemaphore;

//	限流允许的并发数
	private int limitPermits;

//	用来处理的任务数
	private int taskPermits;
	
	private ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
	
	public SemaphoreLimiterExecutor(int limitPermits,int taskPermits){
		this.limitPermits = limitPermits;
		this.taskPermits = taskPermits;
		limitSemaphore = new Semaphore(this.limitPermits);
		syncSemaphore = new Semaphore(this.taskPermits);
	}
	
	public void limitExecute(Runnable runnable){
		try {
			limitSemaphore.acquire();
			executorService.execute(runnable);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally {
			limitSemaphore.release();
			syncSemaphore.release();
		}
	}
	
	public void await(){
		try {
			syncSemaphore.acquire(taskPermits);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		executorService.shutdown();
	}

}
