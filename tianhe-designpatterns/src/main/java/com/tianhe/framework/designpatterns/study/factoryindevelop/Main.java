package com.tianhe.framework.designpatterns.study.factoryindevelop;

import java.util.Scanner;

/**
 * 写一个计算器
 * @author:姜伟锋
 * @DATE:2016年3月13日 @TIME: 上午2:07:05
 */
public class Main {

	/**
	 * java特点
	 * 面向对象、封装、继承、多态（上转型 父类引用指向子类对象，方法重载、方法重写）
	 *
	 * @param args
	 * @author: 姜伟锋
	 * @DATE:2016年3月13日 @TIME: 上午2:15:28
	 */
	public static void main(String[] args) {
//		接受控制台输入
		System.out.println("=================计算器程序");
		System.out.println("输入第一个操作数");
		Scanner scanner = new Scanner(System.in);
		String strNum1 = scanner.nextLine();

		System.out.println("输入运算符");
		String oper = scanner.nextLine();

		System.out.println("输入第二个操作数");
		String strNum2 = scanner.nextLine();
		double result = 0;
		double num1 = Double.parseDouble(strNum1);
		double num2 = Double.parseDouble(strNum2);

		//缺点
		//完全面向过程，不是面向对象、封装，缺少代码重用的好处

//		进行运算
//		if("+".equals(oper)){//常量放前面防止空指针异常
////			result = Double.parseDouble(strNum1) + Double.parseDouble(strNum2);
//			AddOpration opration = new AddOpration();
//			opration.setNum1(num1);
//			opration.setNum2(num2);
//			result = opration.getResult();
//		}else if("-".equals(oper)){
//			result = Double.parseDouble(strNum1) - Double.parseDouble(strNum2);
//		}else if("*".equals(oper)){
//			result = Double.parseDouble(strNum1) * Double.parseDouble(strNum2);
//		}else if("/".equals(oper)){
//			result = Double.parseDouble(strNum1) / Double.parseDouble(strNum2);
//		}

		//可扩展，符合开放，不符合封闭的原则
		//简单工厂设计模式
//		Opration opration = OprationFactory.getOpration(oper);
//		opration.setNum1(num1);
//		opration.setNum2(num2);
//		result = opration.getResult();

		//工厂方法模式 符合开放封闭的原则 可以用反射共性
		//客户端需要知道调用的那个工厂
		if("+".equals(oper)){
			OprationFactory factory = new AddOprationFactory();
			Opration opration = factory.getOpration();
			opration.setNum1(num1);
			opration.setNum2(num2);
			result = opration.getResult();
		}

//		返回结果
		System.out.println(strNum1+oper+strNum2+"="+result);
	}
}
