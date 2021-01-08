package com.tianhe.framework.designpatterns.study.strategy2;

/**
 * 策略模式
 * 优点：恰当的使用继承可以把公共的代码移到父类里面，从而避免重复的代码
 * 策略模式提供了可以替换继承关系的方法
 * 避免使用多重条件转换语句，这种语句不易维护
 * 缺点：客户端必须知道策略类，并自行决定使用哪一种策略，客户端必须知道所有算法和行为
 * 策略模式造成很多的策略类，可以采用享元模式减少对象的数量
 *
 * @author:姜伟锋
 * @DATE:2016年3月13日 @TIME: 下午4:41:56
 */
public class Main {

	public static void main(String[] args) {
		double num = 200;

//		Context context = new Context(new StrategyA());
		//如果添加策略，只需要添加StrategyB类似的实现类，在客户端替换策略即可
		Context context = new Context(new StrategyB());
		double newNum = context.cost(num);
		System.out.println("实际付账=========="+newNum+"元");
	}
}
