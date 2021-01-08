package com.tianhe.framework.designpatterns.study.mediator;

/**
 * 中介者模式 
 * 在Mediator模式中，类之间的交互行为被统一放在 Mediator的对象中，对象通过Mediator对象同其他对象
 * 交互，Mediator对象起着控制器的作用
 * 
 * mediator     
 * 中介者类的抽象父类。 
 * concreteMediator     
 * 具体的中介者类。 
 * colleague 
 * 关联类的抽象父类。
 * concreteColleague 
 * 具体的关联类。
 * 
 * 中介者模式的优点 
 * 1，将系统按功能分割成更小的对象，符合类的最小设计原则 
 * 2，对关联对象的集中控制
 * 3，减小类的耦合程度，明确类之间的相互关系：当类之间的关系过于复杂时，其中任何一个类的修改都会影响到其他类， 
 * 不符合类的设计的开闭原则，而Mediator模式将原来相互依存的多对多的类之间的关系简化为Mediator控制类与其他关联类的
 * 一对多的关系，当其中一个类修改时，可以对其他关联类不产生影响（即使有修改，也集中在Mediator控制类）。
 *  4，有利于提高类的重用性
 * 
 * @author:姜伟锋
 * @DATE:2016年3月19日 @TIME: 下午7:09:32
 */
public class Main {

	/**
	 * 不使用中介者设计模式
	 * 
	 * @param args
	 * @author: 姜伟锋
	 * @DATE:2016年3月19日 @TIME: 下午8:05:19
	 */
	public static void main(String[] args) {
		Person zhangsan = new Man("张三", 5);
		Person lisi = new Man("李四", 6);

		Person xiaofang = new Women("小芳", 6);
		zhangsan.getParter(xiaofang);
		lisi.getParter(xiaofang);
		zhangsan.getParter(lisi);
	}
}
