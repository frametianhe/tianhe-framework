package com.tianhe.framework.designpatterns.study.visitor;

/**
 * 清洁工B，负责公园B部分的卫生情况
 * @author:姜伟锋
 * @DATE:2016年3月20日 @TIME: 下午4:53:35
 */
public class VisitorB implements Visitor {

	@Override
	public void visit(Park park) {

	}

	@Override
	public void visit(ParkA parkA) {

	}

	@Override
	public void visit(ParkB parkB) {
		System.out.println("清洁工B：完成公园"+parkB.getName()+"的卫生");
	}

}
