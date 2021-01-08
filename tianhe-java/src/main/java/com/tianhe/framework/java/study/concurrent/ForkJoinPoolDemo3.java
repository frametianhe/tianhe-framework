package com.tianhe.framework.java.study.concurrent;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * 实现任务分发
 * @author:weifeng.jiang
 * @DATE:2016年12月9日 @TIME: 下午2:19:00
 */
public class ForkJoinPoolDemo3 extends RecursiveTask<Integer>{
	
	@Override
	protected Integer compute() {
		Task taska = new Task(1, 1);
		Task taskb = new Task(2, 2);
		this.invokeAll(taska,taskb);//效率比fork高
		return taska.join()+taskb.join();
	}
	
	public static void main(String[] args) {
		ForkJoinPool pool = new ForkJoinPool();
		ForkJoinPoolDemo3 totalTask = new ForkJoinPoolDemo3();
		ForkJoinTask<Integer> returnTask = pool.submit(totalTask);
		pool.shutdown();
		System.out.println(totalTask.join());
	}
}
