package com.tianhe.framework.disruptor.study.procon;

import com.lmax.disruptor.*;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 一个消息只被一个消费者消费
 * @author:weifeng.jiang
 * @DATE:2017年1月1日 @TIME: 上午1:23:32
 */
public class ProConDemo3 {
	
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
	  
	    // 生产者  
	    SimpleEventProducer producer = new SimpleEventProducer(ringBuffer);  
	    for (int i = 0; i < 5; i++) {
	        producer.onData(String.valueOf(i));  
	    }  
	    workerPool.halt();  
	    executors.shutdown();// 终止线程  
	}  
	
	public static void main(String[] args) throws Exception {
		service();
	}
}

