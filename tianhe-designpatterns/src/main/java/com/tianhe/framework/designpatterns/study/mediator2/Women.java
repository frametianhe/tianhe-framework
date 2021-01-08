package com.tianhe.framework.designpatterns.study.mediator2;

public class Women extends Person {

	public Women(String name, int condition, Mediator mediator) {
		super(name, condition, mediator);
	}

	@Override
	public void getParter(Person person) {
		this.getMediator().setWomen(this);
		//女士相亲找中介
		this.getMediator().getParter(person);
	}

}
