package com.tianhe.framework.designpatterns.study.abstractfactory;

/**
 * 抽象工厂模式
 *
 * 好处：
 * 增加产品族，增加一个产品族和具体工厂，符合开放（可以扩展）封闭（不修改原有的代码）的原则
 * 坏处：
 * 添加产品，修改的地方多
 *
 * 抽象工厂
 * 具体工厂 实例某个产品族的产品对象 南方、北方、温室
 * 抽象 水果
 * 具体产品
 *
 * 抽象工厂是抽象方法模式的进一步抽象
 * 抽象方法是简单工厂的进一步抽象
 *
 * 原则
 * 抽象工厂中方法对应产品结构 苹果 香蕉
 * 具体工厂对应产品族 南方 北方 温室
 *
 *
 * @author:姜伟锋
 * @DATE:2016年3月13日 @TIME: 上午1:25:56
 */
public class Main {

	/**
	 * 客户端
	 * @param args
	 * @author: 姜伟锋
	 * @DATE:2016年3月13日 @TIME: 上午1:42:05
	 */
	public static void main(String[] args) {
		FruitFactory ff = new NorthFruitFactory();
		Fruit apple = ff.getApple();
		apple.get();
		Fruit banana = ff.getBanana();
		banana.get();

		FruitFactory ff2 = new SourthFruitFactory();
		Fruit apple2 = ff2.getApple();
		apple2.get();
		Fruit banana2 = ff2.getBanana();
		banana2.get();

		FruitFactory ff3 = new GreenHourseFactory();
		Fruit apple3 = ff3.getApple();
		apple3.get();
		Fruit banana3 = ff3.getApple();
		banana3.get();

	}
}
