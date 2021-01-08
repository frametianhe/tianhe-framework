package com.tianhe.framework.java.study.concurrent;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 实现任务分发
 * @author:weifeng.jiang
 * @DATE:2016年12月9日 @TIME: 下午2:16:49
 */
public class CyclicBarrierDemo {

	private int num = 0;

	public void service() {
		ExecutorService service = Executors.newCachedThreadPool();
		CyclicBarrier cyclicBarrier = new CyclicBarrier(2,new Runnable() {
			
			@Override
			public void run() {
				System.out.println(num);
			}
		});
		service.execute(new Demo(1, 1, cyclicBarrier));
		service.execute(new Demo(2, 2, cyclicBarrier));
		service.shutdown();
	}

	public static void main(String[] args) {
		CyclicBarrierDemo demo = new CyclicBarrierDemo();
		demo.service();
	}

	class Demo implements Runnable {

		private int a;
		private int b;
		private CyclicBarrier cyclicBarrier;

		public Demo(int a, int b, CyclicBarrier cyclicBarrier) {
			super();
			this.a = a;
			this.b = b;
			this.cyclicBarrier = cyclicBarrier;
		}

		@Override
		public void run() {
			synchronized (CyclicBarrierDemo.class) {
				int total = a + b;
				num += total;
			}
			try {
				cyclicBarrier.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (BrokenBarrierException e) {
				e.printStackTrace();
			}
		}
	}

}
