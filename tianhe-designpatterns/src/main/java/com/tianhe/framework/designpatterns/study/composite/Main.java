package com.tianhe.framework.designpatterns.study.composite;

import java.util.List;

/**
 * 组合模式
 * composite模式也叫组合模式，是构造型的设计模式之一，通过递归手段来构造树形的对象结构，并可以通过一个对象来访问整个
 * 对象树
 * 角色和职责
 * component 树形结构的节点抽象
 * 为所有的对象定义统一的接口，公共属性，行为等的定义
 * 提供管理子节点对象的接口方法
 * 提供管理父节点对象的接口方法
 *
 * leaf 树形结构的叶节点
 * component的实现子类
 *
 * composite 树形结构的枝节点
 * component的实现子类
 * @author:姜伟锋
 * @DATE:2016年3月19日 @TIME: 下午4:08:50
 */
public class Main {

	public static void main(String[] args) {
		//c盘
		Folder rootFolder = new Folder("c:");
		//beifeng目录
		Folder beifengFolder = new Folder("beifeng");
		//beifeng.txt文件
		File beifengFile = new File("beifeng.txt");

		rootFolder.add(beifengFolder);
		rootFolder.add(beifengFile);

		//ibeifeng目录
		Folder ibeifengFolder = new Folder("ibeifeng");
		File ibeifengFile = new File("ibeifeng.txt");
		beifengFolder.add(ibeifengFolder);
		beifengFolder.add(ibeifengFile);

		Folder iibeifengFolder = new Folder("iibeifeng");
		File iibeifengFile = new File("iibeifeng.txt");
		ibeifengFolder.add(iibeifengFolder);
		ibeifengFolder.add(iibeifengFile);

		displayTree(rootFolder,0);
	}

	/**
	 * 递归方法
	 * @param rootFloder
	 * @author: 姜伟锋
	 * @DATE:2016年3月19日 @TIME: 下午4:36:53
	 */
	public static void displayTree(IFile rootFolder,int deep){
		for(int i=0;i<deep;i++){
			System.out.print("==");
		}
		//显示自身名称
		rootFolder.display();
		//获得子树
		List<IFile> children = rootFolder.getChild();
		//遍历子树
		for (IFile iFile : children) {
			if(iFile instanceof File){
				for(int i=0;i<=deep;i++){
					System.out.print("==");
				}
				iFile.display();
			}else{
				displayTree(iFile,deep+1);
			}
		}
	}
}
