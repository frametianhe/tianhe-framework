package com.tianhe.framework.designpatterns.study.mediator2;

public class Man extends Person {

	public Man(String name, int condition, Mediator mediator) {
		super(name, condition, mediator);
	}

	@Override
	public void getParter(Person person) {
		this.getMediator().setMan(this);
		//男士相亲找中介
		this.getMediator().getParter(person);
	}

}
