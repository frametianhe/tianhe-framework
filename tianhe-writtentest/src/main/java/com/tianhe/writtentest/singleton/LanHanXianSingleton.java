package com.tianhe.writtentest.singleton;

/**
 * 这种写法能够在多线程中很好的工作，但是每次调用getInstance方法时都需要进行同步，造成不必要的同步开销，而且大部分时候我们是用不到同步的，所以不建议用这种模式。
 * Created by tianhe on 2020/3/29.
 */
public class LanHanXianSingleton {

    private static LanHanXianSingleton instance;

    private LanHanXianSingleton(){

    }

    public static synchronized LanHanXianSingleton getInstance(){
        if(instance == null){
            instance = new LanHanXianSingleton();
        }
        return instance;
    }
}
