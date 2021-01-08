package com.tianhe.framework.designpatterns.study.cor;

/**
 * 职责链模式
 *
 * 行为模式之一， 该模式构造一系列分别担当不同的职责的类的对 象来共同完成一个任务，这些类的对象之间像链 条一样紧密相连，
 * 所以被称作职责链模式
 *
 优点：
 1。责任的分担。每个类只需要处理自己该处理的工作（不该处理的传递给下一个对象完成），明确各类的责任范围，符合类的最小封装原则。
 2。可以根据需要自由组合工作流程。如工作流程发生变化，可以通过重新分配对象链便可适应新的工作流程。
 3。类与类之间可以以松耦合的形式加以组织。
 缺点：
 因为处理时以链的形式在对象间传递消息，根据实现方式不同，有可能会影响处理的速度。

 *
 * @author:姜伟锋
 * @DATE:2016年3月19日 @TIME: 下午7:47:19
 */
public class Main {

	/**
	 * 不使用职责链设计模式
	 * @param args
	 * @author: 姜伟锋
	 * @DATE:2016年3月19日 @TIME: 下午8:03:11
	 */
	public static void main(String[] args) {
		//缺陷，客户端控制调用的步骤
		//过滤器中的调用就是按流程自动调用
		CarHandler headHandler = new CarHeadHandler();
		CarHandler bodyHandler = new CarBodyHandler();
		CarHandler tailHandler = new CarTailHandler();

		headHandler.handlerCar();
		bodyHandler.handlerCar();
		tailHandler.handlerCar();
	}
}
