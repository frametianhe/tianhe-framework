package com.tianhe.framework.disruptor.study.procon;

import com.lmax.disruptor.EventTranslatorOneArg;
import com.lmax.disruptor.RingBuffer;

/**
 * 生产者
 * @author:weifeng.jiang
 * @DATE:2016年12月30日 @TIME: 上午10:47:10
 */
public class MyEventProduct {

	private RingBuffer<MyEvent> ringBuffer;

	public MyEventProduct(RingBuffer<MyEvent> ringBuffer) {
		super();
		this.ringBuffer = ringBuffer;
	}
	
	private static final EventTranslatorOneArg TRANSLATOR = new EventTranslatorOneArg<MyEvent, Long>() {
		public void translateTo(MyEvent event, long sequence, Long value) {
			event.setValue(value);
		};
	};
	
	public void product(final Long value){
		ringBuffer.publishEvent(TRANSLATOR,value);
	}
}
