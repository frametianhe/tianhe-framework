package com.tianhe.framework.designpatterns.study.state2;

public class Person {

	private int hour;
	private State state;

	public int getHour() {
		return hour;
	}

	public void setHour(int hour) {
		this.hour = hour;
	}

	//不好之处，判断逻辑还在perosn类中
	public void doSomething(){
		if(hour == 7){
			state = new MState();
			state.doSomething();
		}else if(hour == 12){
			state = new LState();
			state.doSomething();
		}else if(hour == 18){
			state = new SState();
			state.doSomething();
		}else{
			state = new NoState();
			state.doSomething();
		}
	}

}
