package com.tianhe.framework.designpatterns.study.dynamicproxy;

/**
 * 真实主题角色
 * @author:姜伟锋
 * @DATE:2016年3月13日 @TIME: 下午9:51:45
 */
public class RealSubject implements Subject{

	@Override
	public void sellBook() {
		System.out.println("卖书");
	}
}
