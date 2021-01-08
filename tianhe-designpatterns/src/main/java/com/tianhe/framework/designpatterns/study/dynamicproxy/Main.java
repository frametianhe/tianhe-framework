package com.tianhe.framework.designpatterns.study.dynamicproxy;

import java.lang.reflect.Proxy;

public class Main {

	public static void main(String[] args) {
		RealSubject realSubject = new RealSubject();
		MyHandler myHandler = new MyHandler();
		myHandler.setRealSubject(realSubject);
		//代理类的类加载器、代理类实现的接口数组、代理类的处理逻辑
		Subject proxySubject = (Subject) Proxy.newProxyInstance(RealSubject.class.getClassLoader(),
				realSubject.getClass().getInterfaces(),myHandler);
		proxySubject.sellBook();
	}
}
