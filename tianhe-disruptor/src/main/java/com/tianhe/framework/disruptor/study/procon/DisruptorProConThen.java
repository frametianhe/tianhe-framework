package com.tianhe.framework.disruptor.study.procon;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 生产者消费者模式，也就是发布订阅模式，不适合线程池模式，也就是主线程分发，先执行消费者1再执行消费者2
 * @author:weifeng.jiang
 * @DATE:2017年1月1日 @TIME: 上午1:29:17
 */
public class DisruptorProConThen {
	
	public static void service() {  
	    ExecutorService executor = Executors.newFixedThreadPool(2);  
	    int ringBufferSize = 8; // RingBuffer 大小，必须是 2 的 N 次方；
	    // 构造Disruptor  
	    Disruptor<SimpleEvent> disruptor = new Disruptor<SimpleEvent>(new SimpleEventFactory(), ringBufferSize, executor,
	    		ProducerType.SINGLE, new BlockingWaitStrategy());
	  
	    disruptor.handleEventsWith(new SimpleHandler2()).then(new SimpleTwoHandler());
	    //这样的方法来定义依赖关系，比如先执行哪个handler再执行哪个handler
	  
	    RingBuffer<SimpleEvent> ringBuffer = disruptor.start();
	  
	    // 生产者  
	    SimpleEventProducer producer = new SimpleEventProducer(ringBuffer);  
	    for (long i = 0; i < 10; i++) {
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

