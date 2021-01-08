package com.tianhe.framework.designpatterns.study.facade;

/**
 * 外观模式
 * facade模式又叫外观模式
 * 为一组具有类似功能的类群，比如类库，子系统等等，提供一个一致的简单的界面，这个一致的简单的界面称为facade
 *
 * 角色和职责
 * facade 为调用方定义简单的调用接口
 * clients 调用者，通过facade接口调用提供某些功能的内部族群
 * packages 功能提供者，指提供功能的类群（模块或子系统）
 * @author:姜伟锋
 * @DATE:2016年3月19日 @TIME: 下午3:33:14
 */
public class Main {

	/**
	 * 不使用外观设计模式
	 * @param args
	 * @author: 姜伟锋
	 * @DATE:2016年3月19日 @TIME: 下午8:03:37
	 */
	public static void main(String[] args) {
		//实现A子系统功能
//		SystemA systemA = new SystemA();
//		systemA.doSomething();
//		
//		SystemB systemB = new SystemB();
//		systemB.doSomething();
//		
//		SystemC systemC = new SystemC();
//		systemC.doSomething();

		Facade facade = new Facade();
		facade.doSomething();
	}
}
