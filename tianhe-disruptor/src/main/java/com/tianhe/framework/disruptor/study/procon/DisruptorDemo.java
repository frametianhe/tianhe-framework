package com.tianhe.framework.disruptor.study.procon;

import com.lmax.disruptor.BatchEventProcessor;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.SequenceBarrier;
import com.lmax.disruptor.YieldingWaitStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 生产者消费者模式，也就是发布订阅者模式
 * @author:weifeng.jiang
 * @DATE:2017年1月1日 @TIME: 上午1:15:28
 */
public class DisruptorDemo {
	
	public static void service() throws Exception {  

		int buffer = 2<<7;
	    final RingBuffer<SimpleEvent> ringBuffer = RingBuffer.createSingleProducer(new SimpleEventFactory(), buffer, new YieldingWaitStrategy());

	    // 创建线程池  
	    ExecutorService executors = Executors.newFixedThreadPool(4);
	  
	    // 创建SequenceBarrier  
	    SequenceBarrier sequenceBarrier = ringBuffer.newBarrier();
		List<BatchEventProcessor<SimpleEvent>> list = new ArrayList<BatchEventProcessor<SimpleEvent>>();

		//消费者要和线程池核心线程数一样
	    for (int i=0;i<4;i++){
			// 创建消息处理器 ,消费者
			BatchEventProcessor<SimpleEvent> processor = new BatchEventProcessor<SimpleEvent>(ringBuffer, sequenceBarrier, new SimpleHandler2());
			// 传入所有消费者线程的序号,如果只有一个消费者的情况可以省略
			ringBuffer.addGatingSequences(processor.getSequence());
			// 把消息处理器提交到线程池
			executors.submit(processor);
			list.add(processor);
		}

	    // 生产者
	    SimpleEventProducer producer = new SimpleEventProducer(ringBuffer);

		//循环次数要大于buffer，否则程序不会结束
		for (int i = 0; i < 300; i++) {
	        producer.onData(String.valueOf(i));  
	    }
		for (BatchEventProcessor<SimpleEvent> processor : list) {
			// 通知事件(或者说消息)处理器可以结束了（并不是马上结束!!!）
			processor.halt();
		}
		executors.shutdown();// 终止线程
	}  
	
	public static void main(String[] args) throws Exception {
		long start = System.currentTimeMillis();
		service();
		long end = System.currentTimeMillis();
		System.out.println((end-start)+" ms");
	}
}

