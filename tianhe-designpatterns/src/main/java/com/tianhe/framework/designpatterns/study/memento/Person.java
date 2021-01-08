package com.tianhe.framework.designpatterns.study.memento;

public class Person {

	//姓名
	private String name;

	//性别
	private String sex;

	//年龄
	private int age;

	public Person(String name, String sex, int age) {
		this.name = name;
		this.sex = sex;
		this.age = age;
	}

	public Person() {

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public void display() {
		System.out.println("Person [name=" + name + ", sex=" + sex + ", age=" + age + "]");
	}


}
