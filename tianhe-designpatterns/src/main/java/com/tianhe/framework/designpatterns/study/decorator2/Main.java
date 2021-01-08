package com.tianhe.framework.designpatterns.study.decorator2;


/**
 * 装饰模式
 * 又叫包装模式，通过一种对客户端透明的方式来扩展对象的功能，是继承关系的一个替换方案
 *
 * 抽象组件角色：一个抽象接口，是被装饰类和装饰类的父接口Car
 * 具体组件角色：抽象组件的实现类 RunCar
 * 抽象装饰角色：包含一个组件的引用，并定义了与抽象组件一致的接口CarDecorator
 * 具体装饰角色：为抽象装饰角色的实现类，负责具体的装饰FlyCarDecorator、SwimCarDecrator
 *
 * @author:姜伟锋
 * @DATE:2016年3月13日 @TIME: 下午3:15:36
 */
public class Main {

	public static void main(String[] args) {
		Car car = new RunCar();
		car.show();
		System.out.println("===============================");
		//只要添加装饰器，在客户端修改装饰器就可以
		Car swimCar = new SwimCarDecrator(car);
		swimCar.show();
		System.out.println("===========================");
		Car flySwimCar = new FlyCarDecorator(swimCar);
		flySwimCar.show();
	}
}
