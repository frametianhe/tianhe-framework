package com.tianhe.framework.disruptor.study.procon;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.YieldingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 实现主线程分发任务在汇总
 * @author:weifeng.jiang
 * @DATE:2016年12月30日 @TIME: 上午10:58:57
 */
public class DisruptorDemo2 {

	public static void main(String[] args) throws Exception{
		ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
		Disruptor<MyEvent> disruptor = new Disruptor<MyEvent>(new MyEventFactory(), 65536,
				executorService,ProducerType.SINGLE,new YieldingWaitStrategy());
//		disruptor.handleExceptionsWith();
		//可以实现自己的exception处理handler
		disruptor.handleEventsWith(new MyEventHandler());
//		disruptor.handleEventsWith(new MyEventHandler()).then(new MyOtherEventHandler());
		//先执行一个handler在执行另一个handler，消费者之间是可以确定执行顺序
		RingBuffer<MyEvent> ringBuffer = disruptor.start();
		MyEventProduct eventHandler = new MyEventProduct(ringBuffer);
		for (long i = 0; i < 10; i++) {
			eventHandler.product(i);
			TimeUnit.SECONDS.sleep(1);
		}
		
		disruptor.shutdown();
		executorService.shutdown();
	}
}
