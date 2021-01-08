package com.tianhe.framework.disruptor.study.procon;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 和demo01的效果一样
 * @author:weifeng.jiang
 * @DATE:2017年1月1日 @TIME: 上午1:29:17
 */
public class Work01 {
	
	public static void service() {  
	    ExecutorService executor = Executors.newFixedThreadPool(2);  
	    int ringBufferSize = 2; // RingBuffer 大小，必须是 2 的 N 次方；  
	    // 构造Disruptor  
	    Disruptor<SimpleEvent> disruptor = new Disruptor<SimpleEvent>(SimpleEvent.FACTORY, ringBufferSize, executor,
	    		ProducerType.SINGLE, new BlockingWaitStrategy());  
	  
	    //每个SimpleHandler (implements EventHandler)都会处理同一条消息
	    disruptor.handleEventsWith(new SimpleHandler("BatchEventHandler_01"),new SimpleHandler("BatchEventHandler_02"));  
	    // 每个EventHandler占用一个线程，如果Executors.newFixedThreadPool(1);会导致阻塞  
	    
//	    disruptor.handleEventsWith(new SimpleHandler("BatchEventHandler_01")).then(new SimpleHandler("BatchEventHandler_02")); 
	    //这样的方法来定义依赖关系，比如先执行哪个handler再执行哪个handler
	  
	    RingBuffer<SimpleEvent> ringBuffer = disruptor.start();  
	  
	    // 生产者  
	    SimpleEventProducer producer = new SimpleEventProducer(ringBuffer);  
	    for (long i = 10; i < 15; i++) {  
	        producer.onData(String.valueOf(i));  
	    }  
	      
	    // 关闭 Disruptor  
	    disruptor.shutdown();// 关闭 disruptor，方法会堵塞，直至所有的事件都得到处理；  
	    executor.shutdown();// 关闭 disruptor 使用的线程池；如果需要的话，必须手动关闭，disruptor在shutdown 时不会自动关闭；  
	}  
	
	public static void main(String[] args) {
		service();
	}
}

