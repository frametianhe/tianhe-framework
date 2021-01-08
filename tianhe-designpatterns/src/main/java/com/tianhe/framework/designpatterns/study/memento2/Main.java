package com.tianhe.framework.designpatterns.study.memento2;

public class Main {

	public static void main(String[] args) {
		//创建
		Person person = new Person("lifengxing","男",24);
		//保存对象内部状态
//		Memento memento = person.createMemento();
		CareTaker careTaker = new CareTaker();
		careTaker.setMemento(person.createMemento());
		person.display();

		System.out.println("=======================");
		//修改
		person.setName("beifeng");
		person.setAge(40);
		person.setSex("女");
		person.display();

		System.out.println("=======================");
		//恢复 还原
//		person.setMemento(memento);
		person.setMemento(careTaker.getMemento());
		person.display();
	}
}
