package com.tianhe.framework.java.study.gdg;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 主线程分发任务
 * @author:weifeng.jiang
 * @DATE:2016年12月31日 @TIME: 下午11:42:40
 */
public class SemaphoreDemo {

	public static void main(String[] args) {
		ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
		final Semaphore semaphore = new Semaphore(0);
		for (int i = 0; i < 10; i++) {
			executor.execute(new Runnable() {
				
				@Override
				public void run() {
					try {
						System.out.println(Thread.currentThread().getName());
					} catch (Exception e) {
						e.printStackTrace();
					}finally{
						semaphore.release(1);
					}
				}
			});
		}
		try {
			semaphore.acquire(10);//主线程等待所有子线程执行完毕
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		executor.shutdown();
	}
}
