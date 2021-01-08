package com.tianhe.framework.java.study.procon;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * cyclicBarrier实现生产者消费者
 * @author:姜伟锋
 * @DATE:2016年7月17日 @TIME: 下午12:32:25
 */
public class CyclicBarrierProCon {

	//把屏障数设置为一个，可以实现互斥通讯
	private final CyclicBarrier cyclicBarrier = new CyclicBarrier(1);
	private static char flag = 'A';
	
	public static void main(String[] args) {
		
		CyclicBarrierProCon more = new CyclicBarrierProCon();
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
					cyclicBarrier.await();//阻塞
					while (flag != 'A') {
						cyclicBarrier.reset();
					}
					System.out.println("线程A==========" + i);
					if (i % 2 == 0) {
						flag = 'B';
					}
				}catch(Exception e){
					e.printStackTrace();
				}finally {
					cyclicBarrier.reset();
				}
			}
		}
	}
	
	private class RunnableB implements Runnable{
		
		@Override
		public void run() {
			for (char c = 'A'; c < 'Z'; c++) {
				try {
					cyclicBarrier.await();
					while (flag != 'B') {
						cyclicBarrier.reset();
					}
					System.out.println("线程B//////////////" + c);
					flag = 'A';
				} catch (Exception e) {
					e.printStackTrace();
				}finally{
					cyclicBarrier.reset();
				}
			}
		}
	}
}
