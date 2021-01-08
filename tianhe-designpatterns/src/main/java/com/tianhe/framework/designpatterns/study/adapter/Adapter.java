package com.tianhe.framework.designpatterns.study.adapter;

/**
 * 使用继承实现适配器模式
 * @author:姜伟锋
 * @DATE:2016年3月19日 @TIME: 下午6:08:13
 */
public class Adapter extends Current{

	public void use18v(){
		System.out.println("使用适配器");
		this.use220V();
	}
}
