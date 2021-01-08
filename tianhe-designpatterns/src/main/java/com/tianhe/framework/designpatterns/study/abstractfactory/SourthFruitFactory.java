package com.tianhe.framework.designpatterns.study.abstractfactory;

public class SourthFruitFactory implements FruitFactory{

	@Override
	public Fruit getApple() {
		return new SourthApple();
	}
	
	@Override
	public Fruit getBanana() {
		return new SourthBanana();
	}
}
