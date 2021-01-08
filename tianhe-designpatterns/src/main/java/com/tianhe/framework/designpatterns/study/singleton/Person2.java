package com.tianhe.framework.designpatterns.study.singleton;

public class Person2 {

	private String name;

	//懒汉式  单线程可以保证单例，多线程不能保证单例
	private static Person2 person;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	//构造函数私有化
	private Person2() {

	}

	//提供一个全局的静态方法
	public static Person2 getPerson(){
		if(person == null){
			//第一个线程没有初始化对象时，第二个线程又进来初始化就不保证单例
			person = new Person2();
		}
		return person;
	}
}
