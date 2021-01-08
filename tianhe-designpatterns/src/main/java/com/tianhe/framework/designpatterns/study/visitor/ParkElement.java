package com.tianhe.framework.designpatterns.study.visitor;

/**
 * 公园每一部分抽象
 * @author:姜伟锋
 * @DATE:2016年3月20日 @TIME: 下午4:45:46
 */
public interface ParkElement {

	//用来接纳访问者
	public void accept(Visitor visitor);
}
