package com.tianhe.framework.designpatterns.study.adapter;

/**
 * 适配器模式
 * adapter模式也叫适配器模式，是构造型模式之一，通过适配器模式可以改变已有类或外部类的接口形式
 * springmvc中用到了适配器模式
 *
 * 对源代码进行大面积修改，降低修改面，只修适配器的类，其它的源代码不用修改
 * 客户端可能随时替换调用的组件，使用适配器调用外部组件，客户端调用适配器，扩展的话只需要修改适配器就可以
 *
 * 1.通过继承实现
 * 2.通过委任实现，也就是聚合
 *
 * 以前版本的代码没有考虑到的一些问题，后来在添加功能，不需要大量修改原来的代码，降低修改面，可以用适配器模式
 * @author:姜伟锋
 * @DATE:2016年3月19日 @TIME: 下午5:46:41
 */
public class Main {

	public static void main(String[] args) {
//		Current current = new Current();//直接使用220v电流有问题
//		current.user220V();

//		Adapter adapter = new Adapter();
//		adapter.use18v();

		Adapter2 adapter = new Adapter2(new Current());
		adapter.use18v();
	}
}
