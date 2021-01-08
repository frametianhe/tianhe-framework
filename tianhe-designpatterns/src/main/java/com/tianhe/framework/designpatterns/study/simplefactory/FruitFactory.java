package com.tianhe.framework.designpatterns.study.simplefactory;

public class FruitFactory {

//	/**
//	 * 获得apple类实例
//	 */
//	public static Fruit getApple(){
//		return new Apple();
//	}
//
//	/**
//	 * 获得banana实例
//	 */
//	public static Fruit getBanana(){
//		return new Banana();
//	}

	/**
	 * get方法，获得所有产品类型
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @throws ClassNotFoundException
	 */
	public static Fruit getFruit(String type) throws InstantiationException, IllegalAccessException, ClassNotFoundException{
//		if(type.equalsIgnoreCase("apple")){
//			return Apple.class.newInstance();
//		}else if(type.equalsIgnoreCase("banana")){
//			return Banana.class.newInstance();
//		}else{
//			System.out.println("找不到实例化的类");
//			return null;
//		}
		Class fruit = Class.forName(type);
		return (Fruit) fruit.newInstance();
	}

}
