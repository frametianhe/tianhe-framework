package com.tianhe.framework.designpatterns.study.cor2;

public abstract class CarHandler {

	protected CarHandler carHandler;

	//连续调用 对象点方法点方法
	public CarHandler setNextHandler(CarHandler carHandler){
		this.carHandler = carHandler;
		return this.carHandler;
	}

	public abstract void handlerCar();
}
