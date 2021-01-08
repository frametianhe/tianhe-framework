package com.tianhe.framework.java.study.gdg;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CompletionServiceDemo {

	public static void main(String[] args) throws Exception {
		ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
		CompletionService completionService = new ExecutorCompletionService<>(executorService);
		for (int i = 0; i < 10; i++) {
			final Integer num = i;
			completionService.submit(new Callable<Integer>() {
				@Override
				public Integer call() throws Exception {
					return num;
				}
			});
		}
		for (int i = 0; i < 10; i++) {
			//谁先执行完，谁先打印（谁先执行完就继续做下面的事情）
			System.out.println(completionService.take().get());
		}
		executorService.shutdown();
	}
}
