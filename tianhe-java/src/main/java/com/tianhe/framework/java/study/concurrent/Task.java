package com.tianhe.framework.java.study.concurrent;

import java.util.concurrent.RecursiveTask;

public class Task extends RecursiveTask<Integer>{
	
	private int a;
	private int b;

	public Task(int a, int b) {
		super();
		this.a = a;
		this.b = b;
	}

	@Override
	protected Integer compute() {
		return a+b;
	}

}
