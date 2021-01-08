package com.tianhe.framework.designpatterns.study.bridge3;

public class Bus extends Car{

	private Engine engine;

	public Bus(Engine engine) {
		super(engine);
	}

	//接口的更高层次的方法
	@Override
	public void installEngine() {
		System.out.print("car:");
		this.getEngine().installEngine();
	}

}
