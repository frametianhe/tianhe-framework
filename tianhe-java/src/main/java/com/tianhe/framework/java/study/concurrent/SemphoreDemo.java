package com.tianhe.framework.java.study.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 实现任务分发
 * @author:weifeng.jiang
 * @DATE:2016年12月9日 @TIME: 下午2:16:23
 */
public class SemphoreDemo {

	private int num = 0;

	public void service() {
		ExecutorService service = Executors.newCachedThreadPool();
		Semaphore semaphore = new Semaphore(0);
		service.execute(new Demo(1, 1, semaphore));
		service.execute(new Demo(2, 2, semaphore));
		try {
			semaphore.acquire(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
//		semaphore.release(2);
		service.shutdown();
		System.out.println(num);
	}

	public static void main(String[] args) {
		SemphoreDemo demo = new SemphoreDemo();
		demo.service();
	}

	class Demo implements Runnable {

		private int a;
		private int b;
		private Semaphore semaphore;

		public Demo(int a, int b, Semaphore semaphore) {
			super();
			this.a = a;
			this.b = b;
			this.semaphore = semaphore;
		}

		@Override
		public void run() {
			try{
				synchronized (SemphoreDemo.class) {
					int total = a + b;
					num += total;
				}
			}catch (Exception e){
				System.out.println("执行任务出错");
			}
			finally{
				semaphore.release(1);
			}
		}
	}

}
