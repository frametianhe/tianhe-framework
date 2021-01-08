package com.tianhe.framework.designpatterns.study.abstractfactory;

public class GreenHourseFactory implements FruitFactory{

	@Override
	public Fruit getApple() {
		return new GreenHourseApple();
	}
	
	@Override
	public Fruit getBanana() {
		return new GreenHourseBanana();
	}
}
