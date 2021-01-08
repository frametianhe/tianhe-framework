package com.tianhe.framework.designpatterns.study.visitor;

/**
 * 访问者
 * @author:姜伟锋
 * @DATE:2016年3月20日 @TIME: 下午4:50:31
 */
public interface Visitor {

	public void visit(Park park);
	public void visit(ParkA parkA);
	public void visit(ParkB parkB);
}
