package com.tianhe.framework.disruptor.study.procon;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.IgnoreExceptionHandler;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.SequenceBarrier;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 使用workPool
 * @author:weifeng.jiang
 * @DATE:2017年1月1日 @TIME: 上午1:31:59
 */
public class Work02 {
	
	public static void service() {  
	    ExecutorService executor = Executors.newFixedThreadPool(2);  
	    int ringBufferSize = 4; // RingBuffer 大小，必须是 2 的 N 次方；  
	    // 构造Disruptor  
	    Disruptor<SimpleEvent> disruptor = new Disruptor<SimpleEvent>(SimpleEvent.FACTORY, ringBufferSize, executor,
	    		ProducerType.SINGLE, new BlockingWaitStrategy());  
	  
	    disruptor.handleExceptionsWith(new IgnoreExceptionHandler());  
	  
	    disruptor.handleEventsWithWorkerPool(new SimpleHandler("WorkEventHandler_01"),new SimpleHandler("WorkEventHandler_02"));  
	  
	    RingBuffer<SimpleEvent> ringBuffer = disruptor.start();  
	  
	    // // 获取生产者的位置信息  
	    SequenceBarrier sequenceBarrier = ringBuffer.newBarrier();  
	  
	    // 生产者  
	    SimpleEventProducer producer = new SimpleEventProducer(ringBuffer);  
	    for (long i = 10; i < 15; i++) {  
	        producer.onData(String.valueOf(i));  
	    }  
	    disruptor.shutdown();  
	    executor.shutdown();  
	}  
	
	public static void main(String[] args) {
		service();
	}
}

