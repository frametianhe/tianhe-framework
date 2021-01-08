package com.tianhe.framework.designpatterns.study.flyweight;

/**
 * 享元模式
 * 享元者分享数据也
 * flyweight模式也叫享元模式，是构造型模式之一，他通过与其他类似对象共享数据来减少内存占用
 *
 * 角色和职责
 * 抽象享元角色：所有具体享元类的父类，规定一些需要实现的公共接口
 * 具体享元模式：
 * 抽象享元模式的具体实现，并实现了抽象享元角色规定的方法
 * 享元工厂角色：负责创建和管理享元模式
 *
 * @author:姜伟锋
 * @DATE:2016年3月13日 @TIME: 下午9:00:45
 */
public class Main {

	/**
	 * 不使用享元设计模式
	 * @param args
	 * @author: 姜伟锋
	 * @DATE:2016年3月19日 @TIME: 下午8:04:20
	 */
	public static void main(String[] args) {
//		MyCharactor myCharactor = new MyCharactor('a');
//		MyCharactor myCharactor2 = new MyCharactor('b');
//		MyCharactor myCharactor3 = new MyCharactor('a');
//		MyCharactor myCharactor4 = new MyCharactor('d');
//		myCharactor.display();
//		myCharactor2.display();
//		myCharactor3.display();
//		myCharactor4.display();
//
//		if(myCharactor == myCharactor3){
//			System.out.println("true");
//		}else{
//			System.out.println("false");
//		}

		//创建MyCharacter工厂
		MyCharactorFactory factory = new MyCharactorFactory();
		//从工厂中取出相应的MyCharacter
		MyCharactor myChar1 = factory.getMyCharactor('a');
		MyCharactor myChar2 = factory.getMyCharactor('b');
		MyCharactor myChar3 = factory.getMyCharactor('a');
		MyCharactor myChar4 = factory.getMyCharactor('d');

		myChar1.display();
		myChar2.display();
		myChar3.display();
		myChar4.display();

		if(myChar1 == myChar3){
			System.out.println("true");
		}else{
			System.out.println("flase");
		}
	}
}
