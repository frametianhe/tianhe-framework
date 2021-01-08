package com.tianhe.framework.designpatterns.study.observer;

/**
 * 观察者模式
 * 当一个对象的状态发生改变时，自动通知其他关联对象，更新关联对象状态
 * 提供给关联对象一种同步通信的手段，使某个对象与依赖它的其他对象之间保持状态同步
 *
 * 被观察者：被观察的对象，当需要被观察的状态发生改变时，需要通知队列中所有观察者对象，被观察者需要维持添加、删除、通知一个
 * 观察者对象的队列列表
 * 被观察者的具体实现，包含一些其他的基本属性和其他操作
 * 观察者
 * 接口或抽象类，当被观察者的状态发生改变时，观察者对象将通过一个回调函数得到通知
 * 观察者的具体实现，得到通知后将完成一些具体的业务逻辑处理
 *
 * 典型应用
 * 侦听事件驱动程序设计中的外部事件
 * 侦听或监听某个对象的状态变化
 * 发布与订阅者模型中，当一个外部事件（新的产品，消息的出现等等）被触发时，通知邮件列表中的订阅者
 *
 * hibernate的session.load()方法也是观察者模式，延迟加载
 *
 * @author:姜伟锋
 * @DATE:2016年3月13日 @TIME: 下午6:43:33
 */
public class Main {

	public static void main(String[] args) {
		Person person = new Person();
		//注册观察者
		person.addObserver(new MyObserver());
		person.addObserver(new MyObserver());
		System.out.println(person.countObservers());
//		person.deleteObservers();
		person.setName("lifengxing");
		person.setAge(23);
		person.setSex("男");
	}
}
