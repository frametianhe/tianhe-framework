package com.tianhe.framework.designpatterns.study.decorator2;

public class SwimCarDecrator extends CarDecorator{

	public SwimCarDecrator(Car car) {
		super(car);
	}

	@Override
	public void show() {
		this.getCar().show();
		this.swim();
	}

	public void swim(){
		System.out.println("可以游");
	}

	@Override
	public void run() {

	}

}
