package com.tianhe.framework.designpatterns.study.state3;

public class NoState extends State{

	@Override
	public void doSomething(Person person) {
		System.out.println(person.getHour()+" 未定义");
	}
}
