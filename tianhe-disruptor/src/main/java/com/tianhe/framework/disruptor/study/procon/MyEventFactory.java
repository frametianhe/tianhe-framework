package com.tianhe.framework.disruptor.study.procon;

import com.lmax.disruptor.EventFactory;

/**
 * rignBuffer通过这个工厂创建event
 * @author:weifeng.jiang
 * @DATE:2016年12月30日 @TIME: 上午10:46:12
 */
public class MyEventFactory implements EventFactory<MyEvent>{

	@Override
	public MyEvent newInstance() {
		return new MyEvent();
	}
}
