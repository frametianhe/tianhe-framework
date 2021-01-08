package com.tianhe.framework.designpatterns.study.observer2;

/**
 * 观察者模式的实现
 * @author:姜伟锋
 * @DATE:2016年3月13日 @TIME: 下午7:24:47
 */
public class Main {

	public static void main(String[] args) {
		BlogUser user = new BlogUser();
		user.addObserver(new MyObserver());
		user.publishBlog("哈哈，博客上线了", "大家都来访问");
	}
}
