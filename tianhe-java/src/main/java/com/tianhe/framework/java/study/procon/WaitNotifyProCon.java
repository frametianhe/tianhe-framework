package com.tianhe.framework.java.study.procon;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * wait notify实现生产者消费者
 * @author:姜伟锋
 * @DATE:2016年7月17日 @TIME: 下午12:32:25
 */
public class WaitNotifyProCon {

	private static char flag = 'A';
	private final Object t = new Object();//多线程使用同一把锁，保证同一时刻只有一个线程工作
	
	public static void main(String[] args) {
		
		WaitNotifyProCon more = new WaitNotifyProCon();
		ExecutorService service = Executors.newCachedThreadPool();
		service.execute(more.new RunnableA());
		service.execute(more.new RunnableB());
		service.shutdown();
	}
	
	private class RunnableA implements Runnable{
		
		@Override
		public void run() {
			for (int i = 1; i < 52; i++) {
				synchronized (t) {
					if(flag != 'A'){
						try {
							t.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					System.out.println("线程A=========="+i);
					if(i%2 == 0){
						flag = 'B';
						t.notifyAll();//这里会唤醒本方和对方，有不必要的资源争抢，用condition更好，只唤醒对方
					}
				}
			}
		}
	}
	
	private class RunnableB implements Runnable{
		
		@Override
		public void run() {
			for (char c = 'A'; c < 'Z'; c++) {
				synchronized (t) {
					if(flag != 'B'){
						try {
							t.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					System.out.println("线程B//////////////"+c);
					flag = 'A';
					t.notifyAll();
				}
			}
		}
	}
}
