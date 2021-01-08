package com.tianhe.framework.designpatterns.study.flyweight2;

import java.util.HashMap;
import java.util.Map;

/**
 * 享元工厂
 * @author:姜伟锋
 * @DATE:2016年3月13日 @TIME: 下午9:35:03
 */
public class TeacherFactory {

	private Map<String, Teacher> pool;

	public TeacherFactory() {
		pool = new HashMap<String, Teacher>();
	}

	public Teacher getTeacher(String number){
		Teacher teacher = pool.get(number);
		if(teacher == null){
			teacher = new Teacher();
			teacher.setNumber(number);
			pool.put(number, teacher);
		}
		return teacher;
	}
}
