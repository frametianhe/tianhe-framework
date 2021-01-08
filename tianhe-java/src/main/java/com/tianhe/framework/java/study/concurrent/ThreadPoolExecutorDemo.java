package com.tianhe.framework.java.study.concurrent;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 有界阻塞队列
 * @author:weifeng.jiang
 * @DATE:2016年12月9日 @TIME: 下午2:11:20
 */
public class ThreadPoolExecutorDemo {
	
	public static void main(String[] args) {
		int queueNum = 4;
		//线程池核心线程数一般是服务器可以执行的线程数  int proccessors = Runtime.getRuntime().availableProcessors();
		int corePoolSize = 2; 
		int maxPoolSize = 6;
		int timeOut = 1;
		// 创建存放线程为4的队列 有界队列  防止资源耗尽
		BlockingQueue<Runnable> queue = new ArrayBlockingQueue<Runnable>(queueNum);
		// 线程池核心线程数（一般cpu可以处理线程数），最大线程数，线程空闲时间（超出会结束线程），时间单位，任务队列
		//ThreadPoolExecutor的构造器不支持传递Callable，只能Runable，前者报异常，后者不报异常
		ThreadPoolExecutor executorService = new ThreadPoolExecutor(corePoolSize,maxPoolSize,timeOut,TimeUnit.MINUTES,queue);
		int listSize = 100;//数据库取值
		int pageSize=5;
		int num = listSize/pageSize;
		if(listSize%pageSize>0){
			num++;
		} 
		CountDownLatch count = new CountDownLatch(num);
		// 执行顺序
		// 核心线程执行，最大线程数-核心线程数（可执行线程数）个线程执行，队列中线程执行
		for (int i = 0; i < num; i++) {
			// 队列满，线程池没有还可以执行的线程（最大线程数-核心线程数）加入队列
			if (queue.size() == 4 && executorService.getPoolSize() >= maxPoolSize) {
				try {
					queue.put(new DemoThread(count));
				} catch (InterruptedException e) {
					System.out.println("任务放入队列异常");
					e.printStackTrace();
				}
			}else{
				executorService.execute(new DemoThread(count));
			}
			System.out.println("队列大小=" + queue.size());
		}
		try {
			//等待所有子线程执行完毕，在执行主线程,用CountDownLatch计数器实现
			count.await();
		} catch (InterruptedException e) {
			//出现异常要么往上抛给框架，提供全局异常页面展示
			//要么在这里记录业务日志，方便查询
			e.printStackTrace();
		}
		executorService.shutdown();
	}

}

class DemoThread implements Runnable{
	
	private CountDownLatch end;
	//还可以通过构造参数把业务对象从主线程传递到子线程
	
	
	public DemoThread(CountDownLatch count) {
		super();
		this.end = count;
	}


	@Override
	public void run(){
		try {
			System.out.println("分页查询数据库数据处理业务逻辑 " + Thread.currentThread().getName());
			Thread.sleep(1000);
//			throw new ThreadException("模拟异常");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally{
			end.countDown();
		}
	}
}
