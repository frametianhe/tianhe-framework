package com.tianhe.framework.java.ext.concurrent;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 用ArrayBlockingQueue封装一个线程同步工具
 *
 * @author jiangweifeng
 *
 */
public class ArrayBlockingQueueCountUpLatch {

	private BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(10);

	private int permits;

	// 只是用来作为存储在队列中的一个元素，没有业务意义
	private String permitsContent = "permits";

	public ArrayBlockingQueueCountUpLatch(int permits) {
		this.permits = permits;
	}

	public void countUp() {
		try {
			blockingQueue.put(permitsContent);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void await() {
		for (int i = 0; i < permits; i++) {
			try {
				blockingQueue.take();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
