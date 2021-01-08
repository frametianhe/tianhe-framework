package com.tianhe.framework.designpatterns.study.factoryindevelop;

public interface OprationFactory {

	/**
	 * 简单工厂，有的地方也叫静态工厂方法
	 * @param oper
	 * @return
	 * @author: 姜伟锋
	 * @DATE:2016年3月13日 @TIME: 上午2:24:05
	 */
	public Opration getOpration();
}
