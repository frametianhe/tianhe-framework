package com.tianhe.framework.designpatterns.study.proxy;

/**
 * 代理主题角色
 * @author:姜伟锋
 * @DATE:2016年3月13日 @TIME: 下午9:52:25
 */
public class ProxySubject implements Subject{

	private RealSubject realSubject;

	@Override
	public void sellBook() {
		discount();
		if(realSubject == null){
			realSubject = new RealSubject();
		}
		this.realSubject.sellBook();
		give();
	}

	public void discount(){
		System.out.println("打折");
	}

	public void give(){
		System.out.println("赠送代金券");
	}
}
