package com.tianhe.framework.designpatterns.study.singleton;

/**
 * 单例模式
 *
 * 应用场景
 * 多个线程之间，比如servlet环境，共享一个资源或者操作同一个对象
 * 在整个程序空间使用全局变量，共享资源
 * 大规模系统中，为了性能考虑，节省对象的创建时间
 *
 * 饿汉式
 * 懒汉式
 * 双重检查
 *
 * @author:姜伟锋
 * @DATE:2016年3月13日 @TIME: 上午2:44:02
 */
public class Main {

	public static void main(String[] args) {
//		Person person = new Person();
//		Person person2 = new Person();
//		person.setName("zahngsan");
//		person2.setName("lsii");
//		System.out.println(person.getName());
//		System.out.println(person2.getName());

//		Person person = Person.getPerson();
//		Person person2 = Person.getPerson();
//		person.setName("zahngsan");
//		person2.setName("lsii");
//		System.out.println(person.getName());
//		System.out.println(person2.getName());

//		Person2 person = Person2.getPerson();
//		Person2 person2 = Person2.getPerson();
//		person.setName("zahngsan");
//		person2.setName("lsii");
//		System.out.println(person.getName());
//		System.out.println(person2.getName());

		Person4 person = Person4.getPerson();
		Person4 person2 = Person4.getPerson();
		person.setName("zahngsan");
		person2.setName("lsii");
		System.out.println(person.getName());
		System.out.println(person2.getName());

	}
}
