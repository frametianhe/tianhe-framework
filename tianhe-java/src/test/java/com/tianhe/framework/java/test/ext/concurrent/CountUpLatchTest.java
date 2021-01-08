package com.tianhe.framework.java.test.ext.concurrent;


import com.tianhe.framework.java.ext.concurrent.CountUpLatch;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author jiangweifeng
 *
 */
public class CountUpLatchTest {

	public static void main(String[] args) {
		ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
		CountUpLatch countUpLatch = new CountUpLatch();
		// 线程数要等于线程同步工具初始化任务数，小于主线程会永远阻塞，大于线程同步工具失去意义
		for (int i = 0; i < 10; i++) {
			executorService.execute(new Runnable() {
				public void run() {
					System.out.println(Thread.currentThread().getName());
					countUpLatch.countUp();
				}
			});
		}
		try {
			countUpLatch.await(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("所有子线程执行完毕");
		executorService.shutdown();
	}

}
