package com.tianhe.framework.designpatterns.study.flyweight2;

/**
 * 享元模式 共享数据，减少占用内存
 * @author:姜伟锋
 * @DATE:2016年3月13日 @TIME: 下午9:37:45
 */
public class Main {

	public static void main(String[] args) {
		TeacherFactory factory = new TeacherFactory();
		Teacher teacher1 = factory.getTeacher("123123");
		Teacher teacher2 = factory.getTeacher("123124");
		Teacher teacher3 = factory.getTeacher("123123");
		Teacher teacher4 = factory.getTeacher("123126");
		System.out.println(teacher1.getNumber());
		System.out.println(teacher2.getNumber());
		System.out.println(teacher3.getNumber());
		System.out.println(teacher4.getNumber());

		if(teacher1 == teacher3){
			System.out.println("true");
		}else{
			System.out.println("false");
		}
	}
}
