package com.tianhe.framework.designpatterns.study.mediator2;


public class Main {

	public static void main(String[] args) {
		Mediator mediator = new Mediator();
		Person zhangsan = new Man("张三", 7, mediator);
		Person lisi = new Man("李四", 7, mediator);

		Person xiaofang = new Women("小芳", 7, mediator);
		zhangsan.getParter(xiaofang);
//		zhangsan.getParter(lisi);
	}
}
