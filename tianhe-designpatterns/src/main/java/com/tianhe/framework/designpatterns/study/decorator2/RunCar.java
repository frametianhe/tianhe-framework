package com.tianhe.framework.designpatterns.study.decorator2;

public class RunCar implements Car{

	@Override
	public void show() {
		this.run();
	}

	public void run(){
		System.out.println("可以跑");
	}

}
