package com.tianhe.framework.disruptor.study.procon;

import java.util.concurrent.ExecutorService;

public class DisruptorExecutorService extends DisruptorExecutor<User> {
	
	private ExecutorService executorService;
	
	public DisruptorExecutorService(ExecutorService executorService) {
		super(executorService);
		this.executorService = executorService;
	}
	
	public DisruptorExecutorService() {
		super();
	}

	public ExecutorService getExecutorService() {
		return executorService;
	}

	public void setExecutorService(ExecutorService executorService) {
		this.executorService = executorService;
	}
}
