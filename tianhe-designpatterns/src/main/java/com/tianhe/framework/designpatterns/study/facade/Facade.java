package com.tianhe.framework.designpatterns.study.facade;

/**
 * 门面
 * @author:姜伟锋
 * @DATE:2016年3月19日 @TIME: 下午3:48:19
 */
public class Facade {

	private SystemA systemA;
	private SystemB systemB;
	private SystemC systemC;

	public Facade() {
		systemA = new SystemA();
		systemB = new SystemB();
		systemC = new SystemC();
	}

	public void doSomething(){
		this.systemA.doSomething();
		this.systemB.doSomething();
		this.systemC.doSomething();
	}

	public void doAB(){
		this.systemA.doSomething();
		this.systemB.doSomething();
	}
}
