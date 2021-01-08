package com.tianhe.framework.java.online.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * 线程池服务,可以限制并发数
 * @author:weifeng.jiang
 * @DATE:2016年12月9日 @TIME: 下午2:08:05
 */
public class SemaphoreExecutor {
	
	private ExecutorService executorService;
	private Semaphore semaphore;
	//信号灯 锁
	
	/**
	 * 初始化线程池
	 * @param poolSize
	 * @param queueSize
	 */
	public SemaphoreExecutor(int poolSize,int queueSize){
		if(poolSize ==0){
			int processors = Runtime.getRuntime().availableProcessors();
			//服务器可以处理线程数
			//无界队列，执行小数量并发，大数量有可能资源耗尽，数据分页小点
			executorService = Executors.newFixedThreadPool(processors);
		}else{
			executorService = Executors.newFixedThreadPool(poolSize);
			//固定线程池
		}
		semaphore = new Semaphore(queueSize+poolSize);
		//semaphore==1的时候可以实现互斥，相当于 wait，notify的情况，类似读写锁
	}
	
	/**
	 * 用线程池调用一个线程，如果线程池和队列占满，将会阻塞
	 * @param runnable
	 * @throws Exception
	 * @author: 姜伟锋
	 * @DATE:2015年12月18日 @TIME: 下午2:55:45
	 */
	public void run(Runnable runnable) throws Exception{
//		限流并发数是1
		semaphore.acquire();
		try {
			//实现Callable接口可以扑捉到异常，实现Runable接口不会
			//线程实现callable接口，重写call方法（不需要返回值），线程池提交用submit方法，会返回future对象
			//线程实现runnable接口，重写run方法，线程池提交用execute方法
			//线程可以用匿名内部类，也可以用内部类（要调用主线程的方法需要放在主类中，类似主类的一个方法，主线程通过子线程的构造器
			//传递参数）
			executorService.execute(runnable);
		} catch (Exception e) {
			throw e;
		}finally{
			semaphore.release();
		}
	}
	
	/**
	 * 等待所有子线程执完毕在执行主线程
	 * @throws Exception
	 * @author: 姜伟锋
	 * @DATE:2015年12月18日 @TIME: 下午2:58:00
	 */
	public void await() throws Exception{
		//等待所有任务执行完毕，关闭线程池
		executorService.shutdown();
		executorService.awaitTermination(10,TimeUnit.MINUTES);
	}

}
