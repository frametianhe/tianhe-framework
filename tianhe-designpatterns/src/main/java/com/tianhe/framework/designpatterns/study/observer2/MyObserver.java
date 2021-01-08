package com.tianhe.framework.designpatterns.study.observer2;

import java.util.Observable;
import java.util.Observer;

/**
 * 订阅者 观察者
 * @author:姜伟锋
 * @DATE:2016年3月13日 @TIME: 下午7:21:04
 */
public class MyObserver implements Observer{

	@Override
	public void update(Observable o, Object arg) {
		Article art = (Article) arg;
		System.out.println("博主发表了新的文章，快去看吧");
		System.out.println("博客标题为====="+art.getArticleTitle());
		System.out.println("博客内容为====="+art.getArticleContent());
	}
}
