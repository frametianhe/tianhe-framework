package com.tianhe.framework.designpatterns.study.bridge2;

public class Bus implements Car{

	//减少了不少子类，要在接口中增加方法和实现，违反了开放封闭的原则

	@Override
	public void install2000Engine() {
		System.out.println("给bus安装2000cc发动机");
	}

	@Override
	public void install2200Engine() {
		System.out.println("给bus安装2200cc发动机");
	}

	@Override
	public void install2300Engine() {
		System.out.println("给bus安装2300cc发动机");
	}

}
