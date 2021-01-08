package com.tianhe.framework.designpatterns.study.visitor;

public class VisitorManager implements Visitor {

	@Override
	public void visit(Park park) {
		System.out.println("管理员：负责公园"+park.getName()+"卫生检查");
	}

	@Override
	public void visit(ParkA parkA) {
		System.out.println("管理员：负责公园"+parkA.getName()+"部分卫生检查");
	}

	@Override
	public void visit(ParkB parkB) {
		System.out.println("管理员：负责公园"+parkB.getName()+"部分卫生检查");
	}

}
