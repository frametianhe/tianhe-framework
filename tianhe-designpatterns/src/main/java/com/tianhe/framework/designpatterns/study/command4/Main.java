package com.tianhe.framework.designpatterns.study.command4;

/**
 * 命令模式
 *
 * 应用场景
 * 1.在面向对象的程序设计中，一个对象调用另一个对象， 一般情况下的调用过程是：创建目标对象实例；设置调
 * 用参数；调用目标对象的方法。但在有些情况下有必要使用一个专门的类对这种调用 过程加以封装，
 * 我们把这种专门的类称作command类。
 * 2.整个调用过程比较繁杂，或者存在多处这种调用。
 *  这时，使用Command类对该调用加以封装，便于功能的 再利用。
 * 调用前后需要对调用参数进行某些处理。
 * 调用前后需要进行某些额外处理，比如日志，缓存，记录历史操作等
 *
 * @author:姜伟锋
 * @DATE:2016年3月20日 @TIME: 下午3:17:17
 */
public class Main {

	// 一个对象调用另一个对象
	public static void main(String[] args) {
		Peddler peddler = new Peddler();
//		peddler.saleApple();
//		peddler.saleBanana();

		Command appleCommand = new AppleCommand(peddler);
		Command bananaCommand = new BananaCommand(peddler);
//		appleCommand.sail();
//		bananaCommand.sail();
		Waiter waiter = new Waiter();

		//下订单
		waiter.setOrder(appleCommand);
		waiter.setOrder(bananaCommand);

		//移除订单
		waiter.removeOrder(appleCommand);
		waiter.sail();

	}
}
