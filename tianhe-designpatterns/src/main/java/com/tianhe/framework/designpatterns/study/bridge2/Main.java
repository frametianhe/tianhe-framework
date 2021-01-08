package com.tianhe.framework.designpatterns.study.bridge2;

/**
 * 桥接模式
 * 角色和职责
 * client bridge的使用者
 * abstraction接口 抽象类接口 接口或者抽象类，维护对行为实现implementor的引用
 * refined abstraction
 * abstraction的子类
 * implementtor
 * 行为实现类接口，abstraction接口定义了基于implementor接口的更高层次的操作
 * concreteImplementor
 * implementor子类
 *
 * @author:姜伟锋
 * @DATE:2016年3月19日 @TIME: 下午5:15:03
 */
public class Main {
	public static void main(String[] args) {
		Car car1 = new Bus();
		car1.install2000Engine();
	}
}
