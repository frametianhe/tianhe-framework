package com.tianhe.framework.java.study.concurrent;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * 分治框架，把几个大任务分成几个小任务执行
 * @author:weifeng.jiang
 * @DATE:2017年1月1日 @TIME: 上午12:13:50
 */
public class ForkJoinPoolDemo extends RecursiveTask<Integer>{
	
	private static final int THRESHOLD = 2;//阀值
	private int start;
	private int end;

	public ForkJoinPoolDemo(int start, int end) {
		super();
		this.start = start;
		this.end = end;
	}

	@Override
	protected Integer compute() {
		int sum = 0;
		//如果任务足够小就执行任务
		boolean canCompute = (end-start) <= THRESHOLD;
		if(canCompute){
			for (int i = start; i <= end; i++) {
				sum +=i;
			}
		}else{
			//如果任务大于阀值，就分成两个子任务执行
			int middle = (start+end) / 2;
			ForkJoinPoolDemo leftTask = new ForkJoinPoolDemo(start, middle);
			ForkJoinPoolDemo rightTask = new ForkJoinPoolDemo(middle+1, end);
			//执行子任务
//			leftTask.join();
//			rightTask.join();
			this.invokeAll(leftTask,rightTask);//效率高
			//等待子任务执行完毕，合并子任务结果
			sum = leftTask.join()+rightTask.join();
		}
		return sum;
	}
	
	public static void main(String[] args) throws Exception {
		ForkJoinPool forkJoinPool = new ForkJoinPool();
		//生成一个计算任务，负责计算1+2+3+4
		ForkJoinPoolDemo task = new ForkJoinPoolDemo(1, 4);
		//执行一个任务
		ForkJoinTask<Integer> result = forkJoinPool.submit(task);
		System.out.println(result.get());
	}

}
