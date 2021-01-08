package com.tianhe.framework.java.study.procon;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程之间交换数据
 * @author:姜伟锋
 * @DATE:2016年7月17日 @TIME: 下午5:35:45
 */
public class ExchangerDemo {

	public static void main(String[] args) {
		ExecutorService service = Executors.newCachedThreadPool();
		final Exchanger exchanger = new Exchanger();
		service.execute(new Runnable() {
			
			@Override
			public void run() {
				String data = "testOne";
				try {
					System.out.println("原数据:"+data+",交换回来的数据:"+exchanger.exchange(data));//阻塞
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		service.execute(new Runnable() {
			
			@Override
			public void run() {
				String data = "testTwo";
				try {
					System.out.println("原数据:"+data+",交换回来的数据:"+exchanger.exchange(data));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		service.shutdown();
	}
}
