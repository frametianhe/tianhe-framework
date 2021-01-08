package com.tianhe.framework.java.ext.concurrent;

import java.util.concurrent.Semaphore;

/**
 * 用Semaphore封装一个线程同步工具
 * 
 * @author jiangweifeng
 *
 */
public class SemaphoreCountUpLatch {

	private Semaphore semaphore;

	private int permits;

	public SemaphoreCountUpLatch(int permits) {
		this.permits = permits;
		semaphore = new Semaphore(0);
	}

	public void countUp() {
		semaphore.release();
	}

	public void await() {
		try {
			semaphore.acquire(permits);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
