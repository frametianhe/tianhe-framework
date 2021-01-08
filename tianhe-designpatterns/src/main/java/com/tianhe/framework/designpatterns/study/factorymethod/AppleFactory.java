package com.tianhe.framework.designpatterns.study.factorymethod;

public class AppleFactory implements FruitFactory{

	@Override
	public Fruit getFruit() {
		return new Apple();
	}
}
