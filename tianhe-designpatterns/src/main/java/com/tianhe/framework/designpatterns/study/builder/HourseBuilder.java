package com.tianhe.framework.designpatterns.study.builder;

/**
 * 工程队
 * @author:姜伟锋
 * @DATE:2016年3月13日 @TIME: 下午2:35:54
 */
public interface HourseBuilder {

	//修地板
	public void makeFloor();

	//修墙
	public void makeWall();

	//修屋顶
	public void makehourseTop();

	public Hourse getHourse();
}
