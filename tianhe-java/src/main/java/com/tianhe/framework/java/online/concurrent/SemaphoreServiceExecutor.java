package com.tianhe.framework.java.online.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * 结算平台线程池服务
 * @author:weifeng.jiang
 * @DATE:2016年12月28日 @TIME: 上午10:09:49
 */
public class SemaphoreServiceExecutor {
	
	private int listSize = 0;
	private int availableProcessors = 0;
	private ExecutorService executorService;
	private Semaphore semaphore = new Semaphore(0);
	
	public SemaphoreServiceExecutor() {}
	
	public SemaphoreServiceExecutor(int listSize,int availableProcessors) {
		this.listSize = listSize;
		this.availableProcessors = availableProcessors;
		init();
	}

	private void init() {
		if(availableProcessors>0){
			executorService = Executors.newFixedThreadPool(availableProcessors);
		}else{
			executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
		}
	}

	public void execute(Runnable runnable) throws Exception{
		try {
			executorService.execute(runnable);
		} catch (Exception e) {
			throw e;
		}finally{
			semaphore.release(1);
		}
	}
	
	public void await() throws Exception{
		semaphore.acquire(listSize);
	}

	public void shutdown() throws Exception{
		executorService.shutdown();
		executorService.awaitTermination(10, TimeUnit.MINUTES);
	}

	public int getListSize() {
		return listSize;
	}

	public void setListSize(int listSize) {
		this.listSize = listSize;
	}

	public int getAvailableProcessors() {
		return availableProcessors;
	}

	public void setAvailableProcessors(int availableProcessors) {
		this.availableProcessors = availableProcessors;
	}
}
