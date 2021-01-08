package com.tianhe.framework.designpatterns.study.decorator2;

/**
 * 装饰对象
 * @author:姜伟锋
 * @DATE:2016年3月13日 @TIME: 下午3:34:10
 */
public abstract class CarDecorator implements Car{

	private Car car;

	public CarDecorator (Car car){
		this.car = car;
	}

	public abstract void show();

	public Car getCar() {
		return car;
	}
}
