package com.tianhe.framework.java.study.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 实现任务分发
 * @author:weifeng.jiang
 * @DATE:2016年12月9日 @TIME: 下午2:17:46
 */
public class CompletionServiceDemo {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ExecutorService service = Executors.newCachedThreadPool();
		CompletionService completionService = new ExecutorCompletionService<>(service);
		completionService.submit(new Callable<Integer>() {
			@Override
			public Integer call() throws Exception {
				return 1+1;
			}
		});
		
		completionService.submit(new Callable<Integer>() {
			@Override
			public Integer call() throws Exception {
				return 2+2;
			}
		});
		
		int num = 0;
		for (int i = 0; i < 2; i++) {
			num += (Integer)(completionService.take().get());//返回值有可能会是null，防止出现异常
			//那个线程先执行完先获得返回值,completionService.poll()无阻塞的效果，先执行完毕的可以做下一步处理
		}
		System.out.println(num);
		service.shutdown();
	}
}
