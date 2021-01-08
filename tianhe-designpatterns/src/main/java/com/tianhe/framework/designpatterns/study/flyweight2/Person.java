package com.tianhe.framework.designpatterns.study.flyweight2;

/**
 * 抽象享元角色
 * @author:姜伟锋
 * @DATE:2016年3月13日 @TIME: 下午9:22:55
 */
public class Person {

	private String name;
	private int age;
	private String sex;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public Person(String name, int age, String sex) {
		super();
		this.name = name;
		this.age = age;
		this.sex = sex;
	}

	public Person() {

	}

}
