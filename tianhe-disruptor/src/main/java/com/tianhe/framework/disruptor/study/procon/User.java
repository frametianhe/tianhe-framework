package com.tianhe.framework.disruptor.study.procon;

public class User {

	private String name;
	private String age;

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public User() {}

	public User(String age, String name) {
		this.age = age;
		this.name = name;
	}

	public User(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "User{" +
				"age='" + age + '\'' +
				", name='" + name + '\'' +
				'}';
	}
}
