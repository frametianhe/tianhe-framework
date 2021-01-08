package com.tianhe.framework.java.test.online.concurrent;


import com.tianhe.framework.java.online.concurrent.SemaphoreExecutor;

public class SettleExecutorTest {
	
	public static void main(String[] args) throws Exception {
		//构造器传递线程数参数有信号灯锁，不传递没有，一般需要传递
		//第一步 创建线程池
		SemaphoreExecutor executor = new SemaphoreExecutor(2, 0);
//		executor.run(new MyThread());
//		executor.run(new MyThread());
//		executor.run(new MyThread());
//		executor.run(new MyThread());
//		executor.run(new MyThread());
		//第二步 提交任务
		//执行步骤
		//刚进来的直接拿着信号灯执行任务，信号灯队列满了以后，再进来的线程阻塞，拿着信号等的任务执行完毕以后释放信号灯，阻塞的线程
		//拿着信号灯去执行任务，知道任务执行完毕
		//类似排队上厕所
		//厕所可以容2个人，也就是2个信号灯
		//刚开始没有人，不用排队直接上，厕所信号灯提示有人，厕所满了以后，在来的人就要排队，进入阻塞状态，前面上完厕所的人出来以后，
		//信号灯厕所提示灯没人，后面排队阻塞的人在去上厕所，厕所信号灯提示有人，直到所有人要上厕所的人上完厕所
//		executor.run(new MyThread());
		for(int i=0;i<10;i++){
			executor.run(new Runnable() {
				
				@Override
				public void run() {
					System.out.println("年后 "+Thread.currentThread().getName());
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			});
		}
		//第三步 等待所有任务执行完毕，关闭线程池
		//等到所有线程任务执行完毕关闭线程池，如果主线程还有代码需要等到子线程执行的结果，所有子线程都要在这行代码之前，提交任务都要在这行代码之前
		//用一个线程池，用两个线程池有问题，主线程不等子线程执行完毕会先执行
		//等到所有子线程执行完毕在执行主线程
		executor.await();
//		executor.run(new MyThread()); 
		//java.util.concurrent.RejectedExecutionException 线程池关闭以后再执行方法就报错，这个类不能用单例模式，每次用之前都要实例化
//		关闭线程池以后再用就要重新实例化，如果要在构造方法中实例化该类，构造方法的这个类就不要用单例模式,确保线程池关闭以后会
//		在创建
	}
	
	

}

class MyThread implements Runnable{
	
	@Override
	public void run() {
		System.out.println("我来了 "+Thread.currentThread().getName());
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
