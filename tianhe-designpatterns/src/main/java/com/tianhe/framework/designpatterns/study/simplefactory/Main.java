package com.tianhe.framework.designpatterns.study.simplefactory;

/**
 * 简单工厂模式
 *
 * FruitFactory 工厂
 * Fruit 抽象
 * Apple、Banana 具体产品
 *
 *
 * 好处：
 * 有利于整个体系优化
 *
 * 坏处：
 * 所有实例化逻辑都在工厂类上，高内聚这方面做的不好
 * 产品增多，工厂类逻辑也要修改，扩展性不好
 * 符合开放（可扩展），不符合封闭的原则（要修改工厂的代码）
 *
 * @author:姜伟锋
 * @DATE:2016年3月12日 @TIME: 下午11:15:40
 */
public class Main {

	public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
//		//实例化一个苹果
//		Apple apple = new Apple();
//		//实例化香蕉
//		Banana banana = new Banana();
//
//		apple.get();
//		banana.get();

//		//实例化苹果、香蕉 用到了多态 上转型
//		//java多态 上转型 父类引用指向子类引用，方法重载，方法重写
//		Fruit apple = new Apple();
//		Fruit banana = new Banana();
//		apple.get();
//		banana.get();

//		//实例化一个apple
//		Fruit apple = FruitFactory.getApple();
//		Fruit banana = FruitFactory.getBanana();
//		apple.get();
//		banana.get();

		Fruit apple = FruitFactory.getFruit("com.tianhe.sheji.simplefactory.Apple");
		Fruit banana = FruitFactory.getFruit("com.tianhe.sheji.simplefactory.Banana");
		apple.get();
		banana.get();
	}
}
