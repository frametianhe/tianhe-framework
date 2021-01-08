package com.tianhe.framework.java.study.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExecutorServiceDemo {

	public static void main(String[] args) throws Exception{
		ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
		List<Future<Integer>> resultList = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			final int num = i;
			Future<Integer> result = executorService.submit(new Callable<Integer>() {
				@Override
				public Integer call() throws Exception {
					return num;
				}
			});
			resultList.add(result);
		}
		
		for (Future<Integer> future : resultList) {
			//第一个执行不完就阻塞，下面的不会执行
			System.out.println(future.get());
		}
	}
}
