package com.tianhe.framework.java.study.concurrent;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 实现任务分发
 * @author:weifeng.jiang
 * @DATE:2016年12月9日 @TIME: 下午2:15:58
 */
public class CountDownLatchDemo {

	private int num = 0;

	public void service() {
		ExecutorService service = Executors.newCachedThreadPool();
		CountDownLatch count = new CountDownLatch(2);
		service.execute(new Demo(1, 1,count));
		service.execute(new Demo(2, 2,count));
		try {
			count.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		service.shutdown();
		System.out.println(num);
	}
	
	public static void main(String[] args) {
		CountDownLatchDemo demo = new CountDownLatchDemo();
		demo.service();
	}

	class Demo implements Runnable {

		private int a;
		private int b;
		private CountDownLatch count;

		public Demo(int a, int b,CountDownLatch count) {
			super();
			this.a = a;
			this.b = b;
			this.count = count;
		}

		@Override
		public void run() {
			synchronized (CountDownLatch.class) {
				int total = a + b;
				num += total;
			}
			count.countDown();
		}
	}

}
