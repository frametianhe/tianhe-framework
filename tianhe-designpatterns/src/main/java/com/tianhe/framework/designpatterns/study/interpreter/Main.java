package com.tianhe.framework.designpatterns.study.interpreter;

import java.util.ArrayList;
import java.util.List;

/**
 * 解释器模式 interpreter模式也叫解释器模式，是行为模式之一，它是一种特殊的设计模式，他建立一个解释器，
 * 对于特定的计算机程序设计语言，用来解释预先定义的方法，简单的说，interpreter模式是一种简单的语法解释器架构
 * 
 * 该文法简单对于复杂的文法, 文法的类层次变得庞大而无法管理。此时语法分析程序生成器这样的工具是更好的选择
 * 
 * Context   解释器上下文环境类。用来存储解释器的上下文环境，比如需要解释的文法等。
 * AbstractExpression    解释器抽象类。
 * ConcreteExpression    解释器具体实现类
 * 
 * 客户端
 * 
 * @author:姜伟锋
 * @DATE:2016年3月19日 @TIME: 下午6:19:21
 */
public class Main {

	public static void main(String[] args) {
		String number = "10";
		Context context = new Context(number);

		// Expression expression = new PlusExpression();
		// expression.interpreter(context);
		// System.out.println(context.getOutput());
		//
		// Expression expression2 = new MinusExpression();
		// expression2.interpreter(context);
		// System.out.println(context.getOutput());

		List<Expression> list = new ArrayList<Expression>();
		list.add(new PlusExpression());
		list.add(new PlusExpression());
		list.add(new MinusExpression());
		list.add(new MinusExpression());
		for (Expression expression : list) {
			expression.interpreter(context);
			System.out.println(context.getOutput());
		}
	}
}
