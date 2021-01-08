package com.tianhe.framework.java.study.gdg;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 线程池分发任务
 * @author:weifeng.jiang
 * @DATE:2016年12月31日 @TIME: 下午11:57:01
 */
public class ExecutorDemo {

	public static void main(String[] args) {
		ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
		for (int i = 0; i < 10; i++) {
			executor.execute(new Runnable() {
				
				@Override
				public void run() {
					System.out.println(Thread.currentThread().getName());
				}
			});
		}
		try {
//			等待5分钟执行完所有任务
			executor.awaitTermination(5, TimeUnit.MINUTES);
//			等待的线程不再接受任务，已接受任务的线程会把任务执行完毕
			executor.shutdown();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
