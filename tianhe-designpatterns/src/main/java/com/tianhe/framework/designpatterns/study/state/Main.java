package com.tianhe.framework.designpatterns.study.state;

/**
 * 状态模式
 * state模式，行为模式之一
 * State模式允许通过改变对象的内部状态 而改变对象的行为，
 * 这个对象表现得就好像修改 了它的类一样
 *
 * 应用场景
 *  状态模式主要解决的是当控制一个对象状态转
 换的条件表达式过于复杂时的情况。把状态的判
 断逻辑转译到表现不同状态的一系列类当中，可
 以把复杂的判断逻辑简化
 *
 * @author:姜伟锋
 * @DATE:2016年3月20日 @TIME: 下午1:47:38
 */
public class Main {

	/**
	 * 不使用状态模式
	 * @param args
	 * @author: 姜伟锋
	 * @DATE:2016年3月20日 @TIME: 下午2:11:12
	 */
	public static void main(String[] args) {
		Person person = new Person();
		//改变对象内部的状态
		person.setHour(7);
		person.doSomething();

		person.setHour(12);
		person.doSomething();

		person.setHour(18);
		person.doSomething();

		person.setHour(8);
		person.doSomething();
	}
}
