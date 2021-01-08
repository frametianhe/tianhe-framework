package com.tianhe.framework.designpatterns.study.singleton;

public class Person3 {

	private String name;

	//懒汉式  单线程可以保证单例，多线程也能保证单例
	private static Person3 person;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	//构造函数私有化
	private Person3() {

	}

	//提供一个全局的静态方法
	public static synchronized Person3 getPerson(){
		if(person == null){//第一个线程没有初始化对象时，第二个线程又进来初始化就不保证单例，可以使用同步方法保证单例
			person = new Person3();//第一次的时候只执行一次
			//只有第一次才会判断person是否等于null，第二次person不等于null，就不会判断了
			//第一次的时候
			//第一个线程拿到锁，判断对象为null，正在创建对象，这时候又进来三个线程，阻塞，第一个线程创建对象后返回，第二个
			//线程拿到锁判断对象不为空返回，第三个线程拿到锁判断对象不为空返回，第四个线程拿到锁判断对象不为空返回
			//第二、三、四个线程阻塞影响性能，以后的每个线程想要拿到对象都要同步判断这个锁，同步方法低效，可以采用同步代码块
			//第二次 都判断对象不为空就返回
		}
		return person;
	}
}
