package com.tianhe.framework.java.study.procon;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * semaphore实现生产者消费者
 * @author:姜伟锋
 * @DATE:2016年7月17日 @TIME: 下午12:32:25
 */
public class SemaphoreProCon {

	//创建只有一个许可的信号量，保证两个线程间每一时刻只有一个在工作
	private final Semaphore semaphore = new Semaphore(1);
	private static char flag = 'A';
	
	public static void main(String[] args) {
		
		SemaphoreProCon more = new SemaphoreProCon();
		ExecutorService service = Executors.newCachedThreadPool();
		service.execute(more.new RunnableA());
		service.execute(more.new RunnableB());
		service.shutdown();
	}
	
	private class RunnableA implements Runnable{
		
		@Override
		public void run() {
			for (int i = 1; i < 52; i++) {
				try {
					semaphore.acquire();
					while (flag != 'A') {
						semaphore.release();
					}
					System.out.println("线程A==========" + i);
					if (i % 2 == 0) {
						flag = 'B';
					}
				}catch(Exception e){
					e.printStackTrace();
				}finally {
					semaphore.release();
				}
			}
		}
	}
	
	private class RunnableB implements Runnable{
		
		@Override
		public void run() {
			for (char c = 'A'; c < 'Z'; c++) {
				try {
					semaphore.acquire();
					while (flag != 'B') {
						semaphore.release();
					}
					System.out.println("线程B//////////////" + c);
					flag = 'A';
				} catch (Exception e) {
					e.printStackTrace();
				}finally{
					semaphore.release();
				}
			}
		}
	}
}
