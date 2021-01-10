package com.tianhe.writtentest.singleton;

/**
 * 懒汉模式申明了一个静态对象，在用户第一次调用时初始化，虽然节约了资源，但第一次加载时需要实例化，反映稍慢一些，而且在多线程不能正常工作。
 * Created by tianhe on 2020/3/29.
 */
public class LanHanSingleton {

    private static LanHanSingleton instance;

    private LanHanSingleton(){

    }

    public static LanHanSingleton getInstance(){
        if(instance == null){
            instance = new LanHanSingleton();
        }
        return instance;
    }
}
