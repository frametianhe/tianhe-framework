package com.tianhe.framework.designpatterns.study.visitor;

/**
 * 公园的A部分
 * @author:姜伟锋
 * @DATE:2016年3月20日 @TIME: 下午4:47:43
 */
public class ParkA implements ParkElement {

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}

}
