package com.tianhe.framework.designpatterns.study.builder;

/**
 * 设计者
 * @author:姜伟锋
 * @DATE:2016年3月13日 @TIME: 下午2:47:32
 */
public class HourseDicretor {

	public void makeHourse(HourseBuilder builder){
		builder.makeFloor();
		builder.makeWall();
		builder.makehourseTop();
	}
}
