package com.tianhe.framework.designpatterns.study.prototype;

import java.util.ArrayList;
import java.util.List;

/**
 * 原型模式
 *
 * 特点
 * 由原型对象自身创建目标对象
 * 目标对象是原型对象的一个克隆
 * 根据对象克隆深度层次的不同，有浅度克隆与深度克隆
 *
 * 应用场景
 * 在创建对象的时候，我们不只是希望被创建的对象继承其基类的基本结构，还希望继承原型对象的数据
 * 希望对目标对象的修改不影响既有的原型的对象，深度克隆的时候也可以完全互补影响
 * 深度克隆操作的细节，很多时候，对对象本身的克隆要涉及到类本身的数据细节
 *
 * @author:姜伟锋
 * @DATE:2016年3月13日 @TIME: 下午1:47:55
 */
public class Main {

	public static void main(String[] args) {
//		Person person = new Person();
//		person.setName("lifengxing");
//		person.setAge(30);
//		person.setSex("男");

//		Person person2 = new Person();
//		person2.setName("beifeng");
//		person2.setAge(30);
//		person2.setSex("男");


//		Person person2 = person.clone();//person和person2是两个不同的对象
//		System.out.println(person.getName());
//		System.out.println(person.getAge());
//		System.out.println(person.getSex());
//		System.out.println(person2.getName());
//		System.out.println(person2.getAge());
//		System.out.println(person2.getSex());

		Person person1 = new Person();
		List<String> friends = new ArrayList<String>();
		friends.add("james");
		friends.add("yao");
		person1.setFriends(friends);

		Person person2 = person1.clone();
		System.out.println(person1.getFriends());
		System.out.println(person2.getFriends());

		friends.add("mike");
		person1.setFriends(friends);
		System.out.println(person1.getFriends());
		System.out.println(person2.getFriends());
	}
}
