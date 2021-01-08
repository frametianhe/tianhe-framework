package com.tianhe.framework.designpatterns.study.templatemethod;

//把公共点抽象出来
public class MakeCa extends MakeCar{

	@Override
	public void makeHead() {
		System.out.println("ca:组装车头");
	}

	@Override
	public void makeBody() {
		System.out.println("ca:组装车身");
	}

	@Override
	public void makeTail() {
		System.out.println("ca:组装车尾");
	}

}
