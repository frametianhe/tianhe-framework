package com.tianhe.framework.java.study.gdg;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 主线程分发任务
 * @author:weifeng.jiang
 * @DATE:2016年12月31日 @TIME: 下午11:37:37
 */
public class CountDownLatchDemo {

	public static void main(String[] args) throws Exception {
		ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
		final CountDownLatch count = new CountDownLatch(10);
		long startTime = System.currentTimeMillis();
		for (int i = 0; i < 1000; i++) {
			final int num = i;
			executor.execute(new Runnable() {
				@Override
				public void run() {
					try {
//						System.out.println(Thread.currentThread().getName());
						System.out.println(Thread.currentThread().getName());
					} catch (Exception e) {
						e.printStackTrace();
					}finally{
						count.countDown();
					}
				}
			});
		}
		try {
			count.await();//主线程等待子线程执行完毕
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		executor.shutdown();
		executor.awaitTermination(10,TimeUnit.MINUTES);//确保主线程输出在子线程之后
//		JOptionPane.showMessageDialog(null, (System.currentTimeMillis()-startTime)+"ms");
		System.out.println((System.currentTimeMillis()-startTime)+"ms");
	}
}
