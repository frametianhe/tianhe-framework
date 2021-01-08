package com.tianhe.framework.disruptor.study.procon;

import com.lmax.disruptor.*;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 一个消息被两组消费者消费，但是每组只能有一个消费
 * @author:weifeng.jiang
 * @DATE:2017年1月1日 @TIME: 上午1:26:30
 */
public class ProConDemo4 {
	
	public static void service() throws Exception {  
	    final RingBuffer<SimpleEvent> ringBuffer = RingBuffer.createSingleProducer(SimpleEvent.FACTORY, 4,new SleepingWaitStrategy());  
	    ExecutorService executors = Executors.newCachedThreadPool();  
	  
	    SequenceBarrier sequenceBarrier = ringBuffer.newBarrier();  
	  
	    // 创建消息处理器 ,消费者  
	    WorkHandler<SimpleEvent> processor1 = new SimpleHandler("WorkHandler_1");  
	    WorkHandler<SimpleEvent> processor2 = new SimpleHandler("WorkHandler_2");  
	    WorkerPool<SimpleEvent> workerPool = new WorkerPool<SimpleEvent>(ringBuffer, sequenceBarrier, 
	    		new IgnoreExceptionHandler(),processor1, processor2);  
	    ringBuffer.addGatingSequences(workerPool.getWorkerSequences());// 要在workerPool.start(executors);之前  
	    workerPool.start(executors);  
	  
	      
	    // 创建消息处理器 ,消费者  
	    WorkerPool<SimpleEvent> workerPool2 = new WorkerPool<SimpleEvent>(ringBuffer, sequenceBarrier, 
	    		new IgnoreExceptionHandler(),new SimpleHandler("WorkHandler_3"));  
	    ringBuffer.addGatingSequences(workerPool2.getWorkerSequences());// 要在workerPool.start(executors);之前  
	    workerPool2.start(executors);  
	      
	    // 生产者  
	    SimpleEventProducer producer = new SimpleEventProducer(ringBuffer);  
	    for (int i = 0; i < 5; i++) {
	        producer.onData(String.valueOf(i));  
	    }  
	    workerPool.halt();//通知消息处理器事件可以结束了，并不是马上结束  
	    executors.shutdown();// 关闭线程池服务，并不是马上关闭，等待所有任务执行完毕  
	}  
	
	public static void main(String[] args) throws Exception {
		service();
	}
}

