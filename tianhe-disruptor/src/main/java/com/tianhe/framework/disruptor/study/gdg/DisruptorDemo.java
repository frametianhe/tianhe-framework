package com.tianhe.framework.disruptor.study.gdg;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.YieldingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import com.tianhe.framework.disruptor.study.procon.SimpleEvent;
import com.tianhe.framework.disruptor.study.procon.SimpleEventProducer;
import com.tianhe.framework.disruptor.study.procon.SimpleHandler;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 主线程分发任务
 * 
 * Disruptor 通过EventFactory在 RingBuffer 中预创建 Event 的实例。一个 Event 实例实际上被用作一个“数据槽”，
 * 发布者发布前，先从RingBuffer 获得一个Event的实例，然后往Event 实例中填充数据，之后再发布到 RingBuffer 中，
 * 之后由Consumer 获得该 Event 实例并从中读取数据
 * @author:weifeng.jiang
 * @DATE:2017年1月1日 @TIME: 上午1:33:47
 */
public class DisruptorDemo {

	public static void main(String[] args) {
		ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
		Disruptor<SimpleEvent> disruptor = new Disruptor<SimpleEvent>(SimpleEvent.FACTORY, 2<<5, executor,
				ProducerType.SINGLE,new YieldingWaitStrategy());
		disruptor.handleEventsWith(new SimpleHandler());
		RingBuffer<SimpleEvent> ringBuffer = disruptor.start();
		
		//生产者
		SimpleEventProducer producer = new SimpleEventProducer(ringBuffer);
		for (int i = 0; i < 1000; i++) {
			producer.onData(i+"");
		}
		
		//关闭disruptor，方法会堵塞，直至所有的事件都得到处理
		disruptor.shutdown();
		//关闭 disruptor 使用的线程池；如果需要的话，必须手动关闭，disruptor在shutdown 时不会自动关闭
		executor.shutdown();
	}
}
