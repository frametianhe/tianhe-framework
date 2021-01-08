package com.tianhe.framework.designpatterns.study.singleton;

public class Person4 {

	private String name;

	//懒汉式 对象延迟加载
	//懒汉式 双重检查  单线程可以保证单例，多线程也能保证单例
	private static Person4 person;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	//构造函数私有化
	private Person4() {

	}

	//提供一个全局的静态方法 双重检查 性能高
	public static Person4 getPerson(){
		//这个判断针对后面的每一次
		if(person == null){//第一个线程没有初始化对象时，第二个线程又进来初始化就不保证单例，可以使用同步方法保证单例
			//可以采取双重判断提升性能
			//只有第一次才会判断person是否等于null，第二次person不等于null，就不会判断了
			//第一次的时候
			//第一个线程拿到锁，创建对象，这时候第二个、第三个、第四个线程阻塞，第一个线程创建对象后返回，第二个、第三个
			//第四个线程都拿到锁判断对象不为空返回，第二次后面的线程判断第一个条件，对象不为空返回，不会阻塞拿到那个锁
			//第二次判断第一个判断对象不为空返回，不用判断锁，减少判断锁的次数，提升性能
			synchronized (Person4.class) {
				if(person == null){//针对第一次
					person = new Person4();//第一次的时候只执行一次
				}
			}
		}
		return person;
	}
}
