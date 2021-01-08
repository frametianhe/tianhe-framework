package com.tianhe.framework.disruptor.study.procon;

import com.lmax.disruptor.EventFactory;

/**
 * 消息组件
 * @author:weifeng.jiang
 * @DATE:2017年1月1日 @TIME: 上午12:49:48
 */
public class SimpleEvent {

	private String value;
	
	public void setValue(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
	
	//消息工厂
	public static final EventFactory<SimpleEvent> FACTORY = new EventFactory<SimpleEvent>() {
		public SimpleEvent newInstance() {
			return new SimpleEvent();
		};
	};
}
