package com.tianhe.framework.designpatterns.study.state3;

public class SState extends State{

	@Override
	public void doSomething(Person person) {
		if(person.getHour() == 18){
			System.out.println("吃晚饭");
		}else{
			person.setState(new NoState());
			person.doSomething();
		}
	}
}
