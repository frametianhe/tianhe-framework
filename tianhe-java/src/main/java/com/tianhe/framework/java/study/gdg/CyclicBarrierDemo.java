package com.tianhe.framework.java.study.gdg;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 主线程分发任务
 * @author:weifeng.jiang
 * @DATE:2016年12月31日 @TIME: 下午11:51:05
 */
public class CyclicBarrierDemo {

	public static void main(String[] args) {
		ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
		final CyclicBarrier cyclicBarrier = new CyclicBarrier(3);
		for (int i = 0; i < 3; i++) {
			executor.execute(new Runnable() {
				
				@Override
				public void run() {
					System.out.println(Thread.currentThread().getName()+" 到门口了");
					try {
						cyclicBarrier.await();
					} catch (InterruptedException e) {
						e.printStackTrace();
					} catch (BrokenBarrierException e) {
						e.printStackTrace();
					}
					System.out.println(Thread.currentThread().getName()+" 到公园了");
					try {
						cyclicBarrier.await();//子线程之间相互等待执行完毕
					} catch (InterruptedException e) {
						e.printStackTrace();
					} catch (BrokenBarrierException e) {
						e.printStackTrace();
					}
				}
			});
		}
		executor.shutdown();
	}
}
