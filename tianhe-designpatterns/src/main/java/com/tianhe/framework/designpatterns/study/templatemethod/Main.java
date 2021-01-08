package com.tianhe.framework.designpatterns.study.templatemethod;

/**
 * 模板方法设计模式
 *
 * 模板方法模式，是 行为模式之一，它把具有特定步骤算法中的某些 必要的处理委让给抽象方法，
 * 通过子类继承对抽 象方法的不同实现改变整个算法的行为
 *
 * 应用场景
 * 统一操作步骤和流程一致
 * 操作细节不同
 * 具有多个同样的步骤，某些细节不同
 *
 * @author:姜伟锋
 * @DATE:2016年3月19日 @TIME: 下午9:48:36
 */
public class Main {

	public static void main(String[] args) {
		MakeCar bus = new MakeBus();
//		bus.makeHead();
//		bus.makeBody();
//		bus.makeTail();
		bus.make();

		System.out.println("========================");
		MakeCar jeep = new MakeJeep();
//		jeep.makeHead();
//		jeep.makeBody();
//		jeep.makeTail();
		jeep.make();

		System.out.println("=================");
		MakeCar ca = new MakeCa();
		ca.make();
	}
}
