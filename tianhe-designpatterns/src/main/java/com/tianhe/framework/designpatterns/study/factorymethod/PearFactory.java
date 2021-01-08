package com.tianhe.framework.designpatterns.study.factorymethod;

public class PearFactory implements FruitFactory{

	@Override
	public Fruit getFruit() {
		return new Pear();
	}
}
