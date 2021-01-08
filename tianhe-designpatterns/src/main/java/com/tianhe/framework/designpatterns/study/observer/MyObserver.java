package com.tianhe.framework.designpatterns.study.observer;

import java.util.Observable;
import java.util.Observer;

public class MyObserver implements Observer{

	//Observer是一个容器

	@Override
	public void update(Observable o, Object arg) {
		System.out.println("对象发生变化");
	}

}
