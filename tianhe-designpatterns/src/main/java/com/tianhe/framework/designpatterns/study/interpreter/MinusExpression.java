package com.tianhe.framework.designpatterns.study.interpreter;

/**
 * 递减解释器
 * @author:姜伟锋
 * @DATE:2016年3月19日 @TIME: 下午6:51:05
 */
public class MinusExpression extends Expression{

	@Override
	public void interpreter(Context context) {
		System.out.println("自动递减");
		String input = context.getInput();
		int intInput = Integer.parseInt(input);
		intInput--;
		context.setInput(intInput+"");
		context.setOutput(intInput);

	}

}
