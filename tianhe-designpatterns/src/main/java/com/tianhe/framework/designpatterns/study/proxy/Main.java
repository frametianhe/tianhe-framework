package com.tianhe.framework.designpatterns.study.proxy;

/**
 * 代理模式
 * proxy模式又叫代理模式
 * 可以为其他对象提供一种代理以控制对这个对象的访问
 *
 * 所谓代理，是指具有与代理元（被代理的对象）具有相同的接口的类，客户端必须通过代理与被代理的目标类交互，而代理一般在交互的
 * 过程中（交互前后）进行某些特别的处理
 *
 * 角色和职责
 * 抽象主题角色：真实主题与代理主题的共同接口
 * 真实主题角色：定义了代理角色所代表的真实对象
 * 代理主题角色：含有对真实主题角色的引用，代理角色通常在将客户端调用传递给真实主题对象之前或者之后执行某些操作，
 * 而不是单独返回真实的对象
 * @author:姜伟锋
 * @DATE:2016年3月13日 @TIME: 下午9:40:14
 */
public class Main {

	public static void main(String[] args) {
//		RealSubject realSubject = new RealSubject();
//		realSubject.sellBook();
//		System.out.println("==============================");
		ProxySubject proxySubject = new ProxySubject();
		proxySubject.sellBook();
	}
}
