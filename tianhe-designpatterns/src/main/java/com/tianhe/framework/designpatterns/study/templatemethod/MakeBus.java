package com.tianhe.framework.designpatterns.study.templatemethod;

public class MakeBus extends MakeCar{

	@Override
	public void makeHead() {
		System.out.println("bus:组装车头");
	}

	@Override
	public void makeBody() {
		System.out.println("bus:组装车身");
	}

	@Override
	public void makeTail() {
		System.out.println("bus:组装车尾");
	}

}
