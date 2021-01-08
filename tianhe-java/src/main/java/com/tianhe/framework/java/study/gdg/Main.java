package com.tianhe.framework.java.study.gdg;

public class Main {

	/**
	 * Synchronizer 闭锁（CountDownLatch，FutureTask ）
	 * 
	 * 实现任务分发再汇总的业务场景，并发包实现
	 *  > ExecutorService线程池，线程实现callable接口，返回future，get方法阻塞
	 *  > CompletionService线程池，线程实现callable接口，返回future，get方法阻塞
	 *  
	 *  > CountDownLatch实现
	 *  > Semaphore实现，semaphore获取不到锁就阻塞
	 *  > CyclicBarrier实现
	 *  > ForkJoinPool实现，线程返回ForkJoinTask，join方法阻塞
	 */
}
