package com.tianhe.framework.java.test.online.concurrent;


import com.tianhe.framework.java.online.concurrent.CountDownLatchServiceExecutor;

public class CountDownLatchServiceExecutorTest {

	public static void main(String[] args) throws Exception{
		CountDownLatchServiceExecutor executor = new CountDownLatchServiceExecutor(10, 2);
		for (int i = 0; i < 10; i++) {
			executor.execute(new Runnable() {
				
				@Override
				public void run() {
					System.out.println(Thread.currentThread().getName()+"============");
				}
			});
		}
		executor.await();
	}
}
