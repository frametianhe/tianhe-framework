package com.tianhe.framework.designpatterns.study.facade;

public class Main2 {

	public static void main(String[] args) {
		// 实现A子系统功能
//		SystemA systemA = new SystemA();
//		systemA.doSomething();
//
//		SystemB systemB = new SystemB();
//		systemB.doSomething();
//
//		SystemC systemC = new SystemC();
//		systemC.doSomething();

		Facade facade = new Facade();
		facade.doAB();
	}
}
