package com.tianhe.framework.designpatterns.study.builder;


/**
 * 建造者模式 builder模式，也叫生成器模式，用来隐藏复合对象的创建过程
 *
 * 应用场景
 * 对象的创建，builder模式是为对象的创建而设计的模式
 * 创建的是一个符合对象，被创建的对象为一个具有符合属性的符合对象
 * 关注对象创建的各个部分的创建过程，不同的工厂（这里指builder生成器）对产品属性有不同的创建方法
 *
 * @author:姜伟锋
 * @DATE:2016年3月13日 @TIME: 下午2:23:30
 */
public class Main {

	public static void main(String[] args) {
		//客户直接造房子
//		Hourse hourse = new Hourse();
//		hourse.setFloor("地板");
//		hourse.setWall("墙");
//		hourse.setHourseTop("屋顶");

		//由工程队来修
//		HourseBuilder builder = new BungalowBuilder();
		//如果增加公寓，只需要增加公寓工程队就可以，可以用反射动态修改
		HourseBuilder builder = new FlatBuilder();
//		//调用工程队来修
//		builder.makeFloor();
//		builder.makehourseTop();
//		builder.makeWall();
		//设计者来做
		HourseDicretor dicretor = new HourseDicretor();
		dicretor.makeHourse(builder);

		Hourse hourse = builder.getHourse();
		System.out.println(hourse.getFloor());
		System.out.println(hourse.getWall());
		System.out.println(hourse.getHourseTop());
	}
}
