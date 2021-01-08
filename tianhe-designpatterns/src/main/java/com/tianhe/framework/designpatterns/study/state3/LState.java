package com.tianhe.framework.designpatterns.study.state3;

public class LState extends State{

	@Override
	public void doSomething(Person person) {
		if(person.getHour() == 12){
			System.out.println("吃午饭");
		}else{
			person.setState(new SState());
			person.doSomething();
		}
	}
}
