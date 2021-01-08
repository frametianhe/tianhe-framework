package com.tianhe.framework.java.study.procon;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * lock实现生产者消费者
 * @author:姜伟锋
 * @DATE:2016年7月17日 @TIME: 下午12:18:16
 */
public class LockProCon {

	private final Lock lock = new ReentrantLock();
	private final Condition conditionA = lock.newCondition();
	private final Condition conditionB = lock.newCondition();
	private static char flag = 'A';
	
	public static void main(String[] args) {
		
		LockProCon more = new LockProCon();
		ExecutorService service = Executors.newCachedThreadPool();
		service.execute(more.new RunnableA());
		service.execute(more.new RunnableB());
		service.shutdown();
	}
	
	private class RunnableA implements Runnable{
		
		@Override
		public void run() {
			for (int i = 1; i < 52; i++) {
				lock.lock();
				try {
					while (flag != 'A') {
						try {
							conditionA.await();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					System.out.println("线程A========"+i);
					if (i % 2 == 0) {
						flag = 'B';
						conditionB.signal();
					}
				} finally {
					lock.unlock();
				}
			}
		}
	}
	
	private class RunnableB implements Runnable{
		
		@Override
		public void run() {
			for (char c='A';c<'Z';c++) {
				lock.lock();
				try {
					while (flag != 'B') {
						try {
							conditionB.await();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					System.out.println("线程B////////////////"+c);
					flag = 'A';
					conditionA.signal();//只唤醒对方
				} finally {
					lock.unlock();
				}
			}
		}
	}
}
