package com.tianhe.framework.designpatterns.study.factorymethod;

/**
 * 工厂方法模式
 *
 * 好处：
 * 不修改工厂角色引进新的产品
 * 符合开放（可扩展）、封闭原则（不修改原来的代码）
 * 坏处：
 * 产品增多，类就越多
 *
 * 抽象工厂 接口
 * 具体工厂 实例化产品对象
 * 抽象角色
 * 具体产品
 *
 * 工厂方法模式和简单工厂模式比较
 * 工厂方法核心是一个抽象工厂类，简单工厂把核心放在一个具体类上
 * 工厂方法也叫多态工厂，具体工厂都有共同的接口或者共同的抽象类，是多态的一种 上转型 父类引用指向子类对象
 * 工厂方法添加新产品时，需要添加一个具体对象和一个和一个具体工厂对象，原有工厂对象不要修改，也不需要修改客户端，复合开放封闭
 * 原则，而简单工厂添加新产品要修改工厂方法，不符合开放封闭的原则，扩展性不好
 * 工厂方法退化后可演变成简单工厂模式
 *
 * @author:姜伟锋
 * @DATE:2016年3月12日 @TIME: 下午11:15:40
 */
public class Main {

	public static void main(String[] args) throws Exception {
		//获得AppleFactory
		FruitFactory ff = new AppleFactory();
		//通过AppleFactory来获得Apple实例对象
		Fruit apple = ff.getFruit();
		apple.get();

		//获得BananaFactory
		FruitFactory ff2 = new BananaFactory();
		Fruit banana = ff2.getFruit();
		banana.get();

		//添加pear新产品，不需要修改之前的代码，复合开放、封闭的原则（ocp）
		//获得PearFactory
		FruitFactory ff3 = new PearFactory();
		Fruit pear = ff3.getFruit();
		pear.get();
	}
}
