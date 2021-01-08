package com.tianhe.framework.designpatterns.study.prototype;

import java.util.ArrayList;
import java.util.List;

public class Person implements Cloneable{

	private String name;//姓名
	private int age;//年龄
	//性别
	private String sex;//属性是深度克隆
	//朋友
	private List<String> friends;//对象是浅度克隆，克隆的只是对象引用，指向的还是一个对象

	public List<String> getFriends() {
		return friends;
	}

	public void setFriends(List<String> friends) {
		this.friends = friends;
	}

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

	public Person clone(){
		try {
			Person person = (Person) super.clone();
			List<String> friends = new ArrayList<String>();
			for (String string : this.getFriends()) {
				friends.add(string);//实现深度克隆
			}
			person.setFriends(friends);
			return person;
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
			return null;
		}
	}

}
