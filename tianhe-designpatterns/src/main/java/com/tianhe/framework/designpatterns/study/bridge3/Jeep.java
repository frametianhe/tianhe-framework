package com.tianhe.framework.designpatterns.study.bridge3;

public class Jeep extends Car{
	
	private Engine engine;

	public Jeep(Engine engine) {
		super(engine);
	}

	@Override
	public void installEngine() {
		System.out.print("jeep:");
		this.getEngine().installEngine();
	}
	
}
