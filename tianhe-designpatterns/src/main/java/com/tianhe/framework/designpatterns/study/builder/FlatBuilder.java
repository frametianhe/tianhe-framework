package com.tianhe.framework.designpatterns.study.builder;

public class FlatBuilder implements HourseBuilder{

	Hourse hourse = new Hourse();

	@Override
	public void makeFloor() {
		hourse.setFloor("公寓地板");
	}

	@Override
	public void makeWall() {
		hourse.setWall("公寓墙");
	}

	@Override
	public void makehourseTop() {
		hourse.setHourseTop("公寓屋顶");
	}

	@Override
	public Hourse getHourse() {
		return hourse;
	}

}
