package com.tianhe.framework.designpatterns.study.strategy;

/**
 * 策略模式
 * strategy模式也叫策略模式，是行为模式之一，对一系列的算法封装，为所有算法定义一个抽象的算法接口，通过继承改抽象算法
 * 接口对所有的算法加以封装和实现，具体的算法选择由客户端决定（策略），strategy模式主要用来平滑的处理算法的切换
 *
 * 角色和职责
 * 策略算法抽象 Strategy
 * 各种策略算法的具体实现 MD5Strategy、MDSStrategy
 * 策略的外部封装类，或者说策略的容器类，根据不同策略执行不同的行为，策略由外部环境决定 Context
 *
 * @author:姜伟锋
 * @DATE:2016年3月13日 @TIME: 下午4:14:26
 */
public class Main {

	/**
	 * 不使用策略设计模式
	 * @param args
	 * @author: 姜伟锋
	 * @DATE:2016年3月19日 @TIME: 下午8:04:56
	 */
	public static void main(String[] args) {
//		Strategy strategy = new MD5Strategy();
//		Strategy strategy = new MDSStrategy();
//		strategy.encrypt();

//		Context context = new Context(new MD5Strategy());
		Context context = new Context(new MDSStrategy());
		context.encrypt();
	}
}
