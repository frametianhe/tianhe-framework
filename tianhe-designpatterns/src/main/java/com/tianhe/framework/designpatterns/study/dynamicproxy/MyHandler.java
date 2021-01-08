package com.tianhe.framework.designpatterns.study.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MyHandler implements InvocationHandler{

	private RealSubject realSubject;//开发中可以使用反射机制

	public void setRealSubject(RealSubject realSubject) {
		this.realSubject = realSubject;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		discount();
		Object result = method.invoke(realSubject, args);
		give();
		return result;
	}

	public void discount(){
		System.out.println("打折");
	}

	public void give(){
		System.out.println("赠送代金券");
	}

}
