package com.tianhe.framework.designpatterns.study.visitor;

/**
 * 访问者模式
 * Visitor模式也叫访问者模式，是行为模式之一 ，
 * 它分离对象的数据和行为，使用Visitor模式， 可以不修改已有类的情况下，增加新的操作
 *
 * 角色和职责
 * 1) 访问者角色（Visitor）： 为该对象结构中具体元素角色声明一个访问操作接口。该操作接
 * 口的名字和参数标识了发送访问请求给具体访问者的具体元素角色。
 *  这样访问者就可以通过该元素角色的特定接口直接访问它。
 *  2) 具体访问者角色（Concrete
 * Visitor）： 实现每个由访问者角色（Visitor）声明的操作。
 * 3) 元素角色（Element）：
 * 定义一个Accept操作，它以一个访问者为参数。
 * 4) 具体元素角色（Concrete Element）： 实现由元素角色提供的Accept操作。
 * 5)对象结构角色（Object Structure）： 这是使用访问者模式必备的角色。
 * 它要具备以下特征：能枚举它的元素；可以提供一个高层的接口以允许该访问者访问它的元 素；
 * 可以是一个复合（组合模式）或是一个集合，如一个列表或一个无序 集合
 *
 * 符合开闭原则
 * 对修改关闭，对扩展开放
 *
 * @author:姜伟锋
 * @DATE:2016年3月20日 @TIME: 下午4:37:49
 */
public class Main {

	public static void main(String[] args) {
		Park park = new Park();
		park.setName("越秀公园");
		Visitor visitorA = new VisitorA();
		park.accept(visitorA);
		Visitor visitorB = new VisitorB();
		park.accept(visitorB);
		VisitorManager visitorManager = new VisitorManager();
		park.accept(visitorManager);
	}
}
