package com.tianhe.framework.designpatterns.study.bridge;

/**
 * 桥接模式
 * bridge模式又叫桥接模式，是构造型的设计模式之一
 * bridge模式基于类的最小设计原则，通过使用封装，聚合以及继承等行为来让不同的类承担不同的责任，它的主要特点是把抽象
 * 与行为实现分离开来，从而可以保持各部分的独立性以及应对它们的功能扩展
 * @author:姜伟锋
 * @DATE:2016年3月19日 @TIME: 下午4:51:55
 */
public class Main {


	//这种方式子类太多
	public static void main(String[] args) {
		Car car1 = new Bus2000();
		car1.installEngine();
	}
}
