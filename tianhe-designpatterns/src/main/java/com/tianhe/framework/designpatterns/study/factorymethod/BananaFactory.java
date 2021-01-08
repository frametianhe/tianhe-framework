package com.tianhe.framework.designpatterns.study.factorymethod;


public class BananaFactory implements FruitFactory{

	@Override
	public Fruit getFruit() {
		return new Banana();
	}
}
