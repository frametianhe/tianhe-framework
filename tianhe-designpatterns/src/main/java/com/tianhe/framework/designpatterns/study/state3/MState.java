package com.tianhe.framework.designpatterns.study.state3;

public class MState extends State{

	@Override
	public void doSomething(Person person) {
		if(person.getHour() == 7){
			System.out.println("吃早饭");
		}else{
			person.setState(new LState());
			person.doSomething();
		}
	}
}
