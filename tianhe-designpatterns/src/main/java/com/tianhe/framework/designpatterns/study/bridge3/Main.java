package com.tianhe.framework.designpatterns.study.bridge3;

/**
 * 桥接模式的实现
 * @author:姜伟锋
 * @DATE:2016年3月19日 @TIME: 下午5:31:19
 */
public class Main {

	public static void main(String[] args) {
		Engine engine2000 = new Engine2000();
		Engine engine2200 = new Engine2200();

		Car car1 = new Bus(engine2000);
		car1.installEngine();

		Car car2 = new Bus(engine2200);
		car2.installEngine();

		Car jeep1 = new Jeep(engine2000);
		jeep1.installEngine();

		Car jeep2 = new Jeep(engine2200);
		jeep2.installEngine();
	}
}
