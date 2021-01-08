package com.tianhe.framework.designpatterns.study.abstractfactory;

/**
 * 产品族
 * @author:姜伟锋
 * @DATE:2016年3月13日 @TIME: 上午1:45:09
 */
public class NorthFruitFactory implements FruitFactory{

	@Override
	public Fruit getApple() {
		return new NorthApple();
	}

	@Override
	public Fruit getBanana() {
		return new NorthBanana();
	}
}
