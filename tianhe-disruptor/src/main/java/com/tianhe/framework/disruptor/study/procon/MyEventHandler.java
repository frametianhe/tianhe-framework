package com.tianhe.framework.disruptor.study.procon;

import com.lmax.disruptor.EventHandler;

/**
 * 怎么处理消息
 * @author:weifeng.jiang
 * @DATE:2016年12月30日 @TIME: 上午10:57:38
 */
public class MyEventHandler implements EventHandler<MyEvent>{

	@Override
	public void onEvent(MyEvent event, long sequence, boolean endOfBatch)
			throws Exception {
		System.out.println("event >>>>>>>>>>>>>>>"+event);
	}
}
