package com.tianhe.writtentest.singleton;

/**
 * 这种方式在类加载时就完成了初始化，所以类加载较慢，但获取对象的速度快。 这种方式基于类加载机制避免了多线程的同步问题
 * 非懒加载模式
 * Created by tianhe on 2020/3/29.
 */
public class EhanSingleton {

    private static EhanSingleton instance = new EhanSingleton();

    private EhanSingleton(){

    }

    public static EhanSingleton getInstance(){
        return instance;
    }
}
