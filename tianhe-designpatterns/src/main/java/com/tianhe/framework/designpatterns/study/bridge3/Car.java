package com.tianhe.framework.designpatterns.study.bridge3;

/**
 * 车和发动机是相互独立的，通过桥接类关联，也就是聚合
 * @author:姜伟锋
 * @DATE:2016年3月19日 @TIME: 下午5:26:02
 */
public abstract class Car {

	private Engine engine;//引用发动机的引用，这里是桥接

	public Car(Engine engine) {
		this.engine = engine;
	}

	public abstract void installEngine();

	public Engine getEngine() {
		return engine;
	}

	public void setEngine(Engine engine) {
		this.engine = engine;
	}

}
