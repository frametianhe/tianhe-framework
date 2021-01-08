package com.tianhe.framework.designpatterns.study.composite;

import java.util.List;

/**
 * 文件节点抽象，是文件和目录的父类
 * 对外界是透明的，安全的
 * @author:姜伟锋
 * @DATE:2016年3月19日 @TIME: 下午4:17:57
 */
public interface IFile {

	//显示文件或文件夹的名字
	public void display();

	//添加
	public boolean add(IFile iFile);

	//移除
	public boolean remove(IFile iFile);

	//获得子节点
	public List<IFile> getChild();
}
