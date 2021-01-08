package com.tianhe.framework.disruptor.study.procon;

import com.lmax.disruptor.BatchEventProcessor;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.SequenceBarrier;
import com.lmax.disruptor.SleepingWaitStrategy;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PronConDemo2 {
	
	public static void service() throws Exception {  
		
	    final RingBuffer<SimpleEvent> ringBuffer = RingBuffer.createSingleProducer(SimpleEvent.FACTORY, 2,new SleepingWaitStrategy());  
	    ExecutorService executors = Executors.newCachedThreadPool();  
	  
	    SequenceBarrier stepOneSequenceBarrier = ringBuffer.newBarrier();  
	    BatchEventProcessor<SimpleEvent> processor1 = new BatchEventProcessor<SimpleEvent>(  
	            ringBuffer, stepOneSequenceBarrier, new SimpleHandler("EventHandler_01"));  
	    executors.submit(processor1);  
	  
	    SequenceBarrier stepTwoSequenceBarrier = ringBuffer.newBarrier(processor1.getSequence());  
	    BatchEventProcessor<SimpleEvent> processor2 = new BatchEventProcessor<SimpleEvent>(  
	            ringBuffer, stepTwoSequenceBarrier, new SimpleHandler("EventHandler_02"));  
	      
	    //processor2消费最慢，所以以processor2的Sequence为阈值  
	    ringBuffer.addGatingSequences(processor2.getSequence());  
	      
	    executors.submit(processor2);  
	  
	    // 生产者  
	    SimpleEventProducer producer = new SimpleEventProducer(ringBuffer);  
	    for (int i = 0; i < 4; i++) {  
	        producer.onData(String.valueOf(i));  
	    }  
	      
	    executors.shutdown();// 终止线程  
	}  
	
	public static void main(String[] args) throws Exception {
		service();
	}
}

