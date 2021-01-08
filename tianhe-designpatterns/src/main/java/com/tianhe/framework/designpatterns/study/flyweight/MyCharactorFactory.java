package com.tianhe.framework.designpatterns.study.flyweight;

import java.util.HashMap;
import java.util.Map;

/**
 * 享元工厂
 * @author:姜伟锋
 * @DATE:2016年3月13日 @TIME: 下午9:10:12
 */
public class MyCharactorFactory {

	private Map<Character, MyCharactor> pool;

	public MyCharactorFactory() {
		pool = new HashMap<Character, MyCharactor>();
	}

	public MyCharactor getMyCharactor(Character character){
		MyCharactor myChar = pool.get(character);
		if(myChar == null){
			myChar = new MyCharactor(character);
			pool.put(character, myChar);
		}
		return myChar;
	}
}
