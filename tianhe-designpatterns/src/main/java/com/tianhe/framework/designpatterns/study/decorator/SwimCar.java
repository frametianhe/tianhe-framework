package com.tianhe.framework.designpatterns.study.decorator;

public class SwimCar implements Car{

	@Override
	public void show() {
		this.run();
		this.swim();
	}

	@Override
	public void run() {
		System.out.println("可以跑");
	}

	public void swim(){
		System.out.println("可以游");
	}

}
