package com.tianhe.framework.designpatterns.study.abstractfactory;

/**
 * 抽象工厂
 * @author:姜伟锋
 * @DATE:2016年3月13日 @TIME: 上午1:45:21
 */
public interface FruitFactory {

	//实例化Apple
	public Fruit getApple();
	//实例化Banana
	public Fruit getBanana();
}
