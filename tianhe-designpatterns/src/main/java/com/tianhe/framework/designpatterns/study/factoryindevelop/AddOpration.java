package com.tianhe.framework.designpatterns.study.factoryindevelop;

public class AddOpration extends Opration{

	@Override
	public double getResult() {
		return this.getNum1() + this.getNum2();
	}
}
