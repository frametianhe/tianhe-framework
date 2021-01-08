package com.tianhe.framework.designpatterns.study.cor2;

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
 如果中间一个环节执行时间长后面的就不会执行

 *
 * @author:姜伟锋
 * @DATE:2016年3月19日 @TIME: 下午7:47:19
 */
public class Main {

	public static void main(String[] args) {
		//缺陷，客户端控制调用的步骤
		//过滤器中的调用就是按流程自动调用
		CarHandler headHandler = new CarHeadHandler();
		CarHandler bodyHandler = new CarBodyHandler();
		CarHandler tailHandler = new CarTailHandler();

//		headHandler.handlerCar();
//		bodyHandler.handlerCar();
//		tailHandler.handlerCar();

		//组装顺序预先设定好，顺序是车头-》车身-》车尾
		//顺序可以写到配置文件中，struts2的拦截器中拦截器执行顺序就是在struts2.xml配置文件中
		headHandler.setNextHandler(bodyHandler);
		bodyHandler.setNextHandler(tailHandler);

		//调用职责链的链头来完成操作
		headHandler.handlerCar();

		System.out.println("==========================");
		//顺序改变，希望是车身-》车头-》车尾
		bodyHandler.setNextHandler(headHandler);
		headHandler.setNextHandler(tailHandler);
		bodyHandler.handlerCar();

		System.out.println("======================");
		//简单操作
		bodyHandler.setNextHandler(headHandler).setNextHandler(tailHandler);
		bodyHandler.handlerCar();
	}
}
