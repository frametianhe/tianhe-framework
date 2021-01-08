package com.tianhe.framework.java.study.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 实现任务分发
 * @author:weifeng.jiang
 * @DATE:2016年12月9日 @TIME: 下午2:17:21
 */
public class CallableDemo {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ExecutorService service = Executors.newCachedThreadPool();
		Future<Integer> future1 = service.submit(new Callable<Integer>() {

			@Override
			public Integer call() throws Exception {
				return 1+1;
			}
			
		});
		
		Future<Integer> future2 = service.submit(new Callable<Integer>() {
			
			@Override
			public Integer call() throws Exception {
				return 2+2;
			}
			
		});
		
		service.shutdown();
		int total = future1.get()+future2.get();//如果没执行完毕，就阻塞
		System.out.println(total);
	}
}
