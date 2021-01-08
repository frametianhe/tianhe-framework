package com.tianhe.framework.designpatterns.study.memento;

/**
 * 备忘录模式
 *
 * 保存对象状态
 * @author:姜伟锋
 * @DATE:2016年3月19日 @TIME: 下午10:13:34
 */
public class Main {

	//不用备忘录设计模式
	public static void main(String[] args) {
		Person person = new Person("lifengxing", "男", 20);

		//保存内部状态
		Person backup = new Person();
		backup.setName(person.getName());
		backup.setAge(person.getAge());
		backup.setSex(person.getSex());

		person.display();

		//修改
		person.setAge(30);
		person.display();

		//回滚 还原
		person.setAge(backup.getAge());
		person.setName(backup.getName());
		person.setSex(backup.getSex());
		person.display();
	}
}
