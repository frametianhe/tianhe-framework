package com.tianhe.framework.designpatterns.study.decorator;

/**
 * 装饰模式
 * 又叫包装模式，通过一种对客户端透明的方式来扩展对象的功能，是继承关系的一个替换方案
 *
 * 这个版本可以扩展添加继承car的子类，用继承来实现
 *
 * @author:姜伟锋
 * @DATE:2016年3月13日 @TIME: 下午3:15:36
 */
public class Main {

	/**
	 * 不使用装饰设计模式
	 * @param args
	 * @author: 姜伟锋
	 * @DATE:2016年3月19日 @TIME: 下午8:04:01
	 */
	public static void main(String[] args) {
		Car flyCar = new SwimCar();
		flyCar.show();
	}
}
