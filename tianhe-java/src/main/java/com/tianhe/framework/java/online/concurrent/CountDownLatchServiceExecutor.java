package com.tianhe.framework.java.online.concurrent;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程池服务
 * @author:weifeng.jiang
 * @DATE:2016年12月28日 @TIME: 上午10:09:49
 */
public class CountDownLatchServiceExecutor {
	
	private int listSize = 0;
	private int availableProcessors = 0;
	private ExecutorService executorService;
	private CountDownLatch count;
	
	public CountDownLatchServiceExecutor() {}
	
	public CountDownLatchServiceExecutor(int listSize,int availableProcessors) {
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
		 count = new CountDownLatch(listSize);
	}

	public void execute(Runnable runnable) throws Exception{
		try {
			executorService.execute(runnable);
		} catch (Exception e) {
			throw e;
		}finally{
			count.countDown();
		}
	}
	
	public void await() throws Exception{
		count.await();
		executorService.shutdown();
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
