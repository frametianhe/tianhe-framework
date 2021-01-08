package com.tianhe.framework.designpatterns.study.builder;

/**
 * 平房工程队
 * @author:姜伟锋
 * @DATE:2016年3月13日 @TIME: 下午2:36:04
 */
public class BungalowBuilder implements HourseBuilder{

	Hourse hourse = new Hourse();

	@Override
	public void makeFloor() {
		hourse.setFloor("平房地板");
	}

	@Override
	public void makehourseTop() {
		hourse.setHourseTop("平房屋顶");
	}

	@Override
	public void makeWall() {
		hourse.setWall("平房墙");
	}

	@Override
	public Hourse getHourse() {
		return hourse;
	}
}
