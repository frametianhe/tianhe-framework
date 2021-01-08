package com.tianhe.framework.disruptor.study.procon;

import com.lmax.disruptor.BatchEventProcessor;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.SequenceBarrier;
import com.lmax.disruptor.SleepingWaitStrategy;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 每个event都被处理了，但是先后顺序不同
 * @author:weifeng.jiang
 * @DATE:2017年1月1日 @TIME: 上午1:15:28
 */
public class ProConDemo {
	
	public static void service() throws Exception {  
	    /* 
	     * createSingleProducer创建一个单生产者的RingBuffer， 
	     * 第一个参数叫EventFactory，从名字上理解就是“事件工厂”，其实它的职责就是产生数据填充RingBuffer的区块。 
	     * 第二个参数是RingBuffer的大小，它必须是2的指数倍 目的是为了将求模运算转为&运算提高效率 
	     * 第三个参数是RingBuffer的生产都在没有可用区块的时候(可能是消费者（或者说是事件处理器） 太慢了)的等待策略 
	     */  
	    final RingBuffer<SimpleEvent> ringBuffer = RingBuffer.createSingleProducer(SimpleEvent.FACTORY, 2,new SleepingWaitStrategy());  
	    // 创建线程池  
	    ExecutorService executors = Executors.newCachedThreadPool();  
	  
	    // 创建SequenceBarrier  
	    SequenceBarrier sequenceBarrier = ringBuffer.newBarrier();  
	  
	    // 创建消息处理器 ,消费者  
	    BatchEventProcessor<SimpleEvent> processor1 = new BatchEventProcessor<SimpleEvent>(  
	            ringBuffer, sequenceBarrier, new SimpleHandler("EventHandler_01"));  
	    // 传入所有消费者线程的序号,如果只有一个消费者的情况可以省略  
	    ringBuffer.addGatingSequences(processor1.getSequence());  
	    // 把消息处理器提交到线程池  
	    executors.submit(processor1);  
	    // 如果存在多个消费者 那重复执行上面3行代码 把SimpleHandler换成其它消费者类  
	  
	    BatchEventProcessor<SimpleEvent> processor2 = new BatchEventProcessor<SimpleEvent>(  
	            ringBuffer, sequenceBarrier, new SimpleHandler("EventHandler_02"));  
	    ringBuffer.addGatingSequences(processor2.getSequence());  
	    executors.submit(processor2);  
	    // 两个消费者会消费一样的事件  
	  
	    // 生产者  
	    SimpleEventProducer producer = new SimpleEventProducer(ringBuffer);  
	    for (int i = 0; i < 40; i++) {
	        producer.onData(String.valueOf(i));  
	    }  
	    processor1.halt();// 通知事件(或者说消息)处理器可以结束了（并不是马上结束!!!）  
	    processor2.halt();  
	    executors.shutdown();// 终止线程  
	}  
	
	public static void main(String[] args) throws Exception {
		service();
	}
}

