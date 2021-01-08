package com.tianhe.framework.designpatterns.study.visitor;

public class Park implements ParkElement {
	
	private String name;
	
	private ParkA parkA;
	
	private ParkB parkB;

	public Park() {
		this.parkA = new ParkA();
		this.parkB = new ParkB();
		parkA.setName("A");
		parkB.setName("B");
	}

	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
		parkA.accept(visitor);
		parkB.accept(visitor);
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
