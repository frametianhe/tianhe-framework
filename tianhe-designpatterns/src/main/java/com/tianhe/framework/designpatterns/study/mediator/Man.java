package com.tianhe.framework.designpatterns.study.mediator;

public class Man extends Person{

	public Man(String name, int condition) {
		super(name, condition);
	}

	@Override
	public void getParter(Person person) {
		if(person instanceof Man){
			System.out.println("我不是同性恋");
		}else{
			if(this.getCondition() == person.getCondition()){
				System.out.println(this.getName()+"和"+person.getName()+"绝配");
			}else{
				System.out.println(this.getName()+"和"+person.getName()+"不配");
			}
		}
	}

}
