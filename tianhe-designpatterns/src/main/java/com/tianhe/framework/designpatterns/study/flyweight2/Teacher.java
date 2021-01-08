package com.tianhe.framework.designpatterns.study.flyweight2;

/**
 * 具体享元角色
 * @author:姜伟锋
 * @DATE:2016年3月13日 @TIME: 下午9:25:17
 */
public class Teacher extends Person{

	private String number;

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Teacher(String name, int age, String sex, String number) {
		super(name, age, sex);
		this.number = number;
	}

	public Teacher() {
		super();
	}

}
