package com.tianhe.framework.designpatterns.study.interpreter;

/**
 * 递增解释器
 * @author:姜伟锋
 * @DATE:2016年3月19日 @TIME: 下午6:45:26
 */
public class PlusExpression extends Expression{

	@Override
	public void interpreter(Context context) {
		//提示信息
		System.out.println("自动递增");
		//获得上下文环境
		String input = context.getInput();
		//进行类型转换
		int intInput = Integer.parseInt(input);
		//进行自增
		intInput++;
		//对上下文环境重新进行赋值
		context.setInput(intInput+"");
		context.setOutput(intInput);
	}

}
