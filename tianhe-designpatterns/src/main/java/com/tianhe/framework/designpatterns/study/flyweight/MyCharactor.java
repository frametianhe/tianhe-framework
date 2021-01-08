package com.tianhe.framework.designpatterns.study.flyweight;

/**
 * 具体享元角色
 * @author:姜伟锋
 * @DATE:2016年3月13日 @TIME: 下午9:09:44
 */
public class MyCharactor {

	private char mychar;

	public MyCharactor(char mychar) {
		this.mychar = mychar;
	}

	public void display(){
		System.out.println(mychar);
	}

}
