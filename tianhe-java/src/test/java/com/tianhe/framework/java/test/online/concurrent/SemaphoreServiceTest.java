package com.tianhe.framework.java.test.online.concurrent;


import com.tianhe.framework.java.online.concurrent.SemaphoreServiceExecutor;

public class SemaphoreServiceTest {

	public static void main(String[] args) throws Exception{
		SemaphoreServiceExecutor executor = new SemaphoreServiceExecutor(10, 2);
		for (int i = 0; i < 10; i++) {
			executor.execute(new Runnable() {
				
				@Override
				public void run() {
					System.out.println(Thread.currentThread().getName()+"============");
				}
			});
		}
		executor.await();
		executor.shutdown();
	}
}
