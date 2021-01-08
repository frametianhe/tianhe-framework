package com.tianhe.framework.designpatterns.study.factoryindevelop;

public abstract class Opration {

	private double num1;
	private double num2;

	public double getNum1() {
		return num1;
	}
	public void setNum1(double num1) {
		this.num1 = num1;
	}
	public double getNum2() {
		return num2;
	}
	public void setNum2(double num2) {
		this.num2 = num2;
	}

	/**
	 * 模板方法设计模式
	 * @return
	 * @author: 姜伟锋
	 * @DATE:2016年3月13日 @TIME: 上午2:18:45
	 */
	public abstract double getResult();

}
