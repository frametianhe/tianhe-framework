package com.tianhe.framework.designpatterns.study.singleton;

public class Person {

	//饿汉式 单线程、多线程都可以保证单例
	public static final Person person = new Person();

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	//构造函数私有化
	private Person() {

	}

	//提供一个全局的静态方法
	public static Person getPerson(){
//		return new Person();
		//共享数据就一行代码线程安全
		return person;
	}
}
