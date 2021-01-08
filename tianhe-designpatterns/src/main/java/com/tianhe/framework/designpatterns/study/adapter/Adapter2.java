package com.tianhe.framework.designpatterns.study.adapter;

/**
 * 继承实现的适配器模式
 * 使用者最多，不需要再继承了，聚合只是一个引用
 * @author:姜伟锋
 * @DATE:2016年3月19日 @TIME: 下午6:07:35
 */
public class Adapter2 {

	private Current current;//调用组件的引用

	public Adapter2(Current current) {
		this.current = current;
	}

	public void use18v(){
		System.out.println("使用适配器");
		current.use220V();
	}

}
