package com.tianhe.framework.designpatterns.study.visitor;

/**
 * 清洁工A，负责公园A部分的卫生情况
 * @author:姜伟锋
 * @DATE:2016年3月20日 @TIME: 下午4:52:01
 */
public class VisitorA implements Visitor {

	@Override
	public void visit(Park park) {

	}

	@Override
	public void visit(ParkA parkA) {
		System.out.println("清洁工A：完成公园"+parkA.getName()+"的卫生");
	}

	@Override
	public void visit(ParkB parkB) {

	}

}
