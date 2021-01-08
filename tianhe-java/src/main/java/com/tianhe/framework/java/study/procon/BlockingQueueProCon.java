package com.tianhe.framework.java.study.procon;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;


/**
 * 用两个阻塞队列可以实现生产者消费者，队列长度都是1
 * 
 * @author:姜伟锋
 * @DATE:2016年6月26日 @TIME: 下午8:48:04
 */
public class BlockingQueueProCon {

	public static void main(String[] args) {
		final Business business = new Business();
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				for (int i = 0; i < 50; i++) {
						business.sub(i);
				}
			}
		}).start();
		
		for (int i = 0; i < 50; i++) {
				business.main(i);
		}
	}
	
	static class Business{
		
		BlockingQueue<Integer> queue1 = new ArrayBlockingQueue<>(1);
		BlockingQueue<Integer> queue2 = new ArrayBlockingQueue<>(1);
		
		{//匿名构造方法，也叫方法块，比构造方法先运行
			try {
				queue2.put(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		public void sub(int i){
			
			try {
				queue1.put(1);//阻塞
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			for (int j = 0; j < 10; j++) {
				System.out.println("sub thread sequece of " + j + ",loop is"+ i);
			}
			try {
				queue2.take();//阻塞
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		public void main(int i){
			
			try {
				queue2.put(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			for (int j = 0; j < 10; j++) {
				System.out.println("main thread sequece of " + j + ",loop is"+ i);
			}
			
			try {
				queue1.take();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}


