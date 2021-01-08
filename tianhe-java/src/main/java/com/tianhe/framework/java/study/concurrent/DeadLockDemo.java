package com.tianhe.framework.java.study.concurrent;

/**
 * 死锁实现
 * @author:weifeng.jiang
 * @DATE:2016年12月9日 @TIME: 下午2:13:39
 */
public class DeadLockDemo {
	
	private static Object lockA = new Object();
	private static Object lockB = new Object();
	
	public static void main(String[] args) {
		new Thread(){public void run() {test01();};}.start();
		new Thread(){public void run() {test02();};}.start();
	}

	private static void test02() {
		
		synchronized (lockA) {
			for (int i = 0; i < 10; i++) {
				System.out.println(Thread.currentThread().getName()+"...A...i..." + i);
				synchronized (lockB) {
					for (int j = 0; j < 10; j++) {
						System.out.println(Thread.currentThread().getName()+"...A...j..." + j);
					}
				}
			}
		}
	}

	private static void test01() {
		
		synchronized (lockB) {
			for (int i = 0; i < 10; i++) {
				System.out.println(Thread.currentThread().getName()+"...B...i..." + i);
				synchronized (lockA) {
					for (int j = 0; j < 10; j++) {
						System.out.println(Thread.currentThread().getName()+"...B...j..." + j);
					}
				}
			}
		}
	}
	
	

}
