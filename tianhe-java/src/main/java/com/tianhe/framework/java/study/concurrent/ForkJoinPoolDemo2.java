package com.tianhe.framework.java.study.concurrent;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class ForkJoinPoolDemo2 extends RecursiveAction{

	@Override
	protected void compute() {
		System.out.println(Thread.currentThread().getName());
	}
	
	public static void main(String[] args) {
		ForkJoinPool forkJoinPool = new ForkJoinPool();
		for (int i = 0; i < 10; i++) {
			forkJoinPool.execute(new ForkJoinPoolDemo2());
		}
		forkJoinPool.shutdown();
	}

}
