package com.tianhe.framework.designpatterns.study.observer2;

import java.util.Observable;

/**
 * 发布者 被观察者
 * @author:姜伟锋
 * @DATE:2016年3月13日 @TIME: 下午7:21:12
 */
public class BlogUser extends Observable{

	public void publishBlog(String articleTitle,String articleContent){
		Article art = new Article();
		art.setArticleTitle(articleTitle);
		art.setArticleContent(articleContent);
		System.out.println("博主:发表新文章，文章标题="+articleTitle+"，文章内容="+articleContent);
		this.setChanged();
		this.notifyObservers(art);
	}
}
