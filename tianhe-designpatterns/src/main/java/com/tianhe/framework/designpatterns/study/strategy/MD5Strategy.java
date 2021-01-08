package com.tianhe.framework.designpatterns.study.strategy;

public class MD5Strategy implements Strategy{

	@Override
	public void encrypt() {
		System.out.println("执行md5加密");
	}

}
