package com.tianhe.writtentest.singleton;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * 用SingletonManager 将多种的单例类统一管理，在使用时根据key获取对象对应类型的对象。这种方式使得我们可以管理多种类型的单例，
 * 并且在使用时可以通过统一的接口进行获取操作，降低了用户的使用成本，也对用户隐藏了具体实现，降低了耦合度。
 * Created by tianhe on 2020/3/29.
 */
public class SingletonManager {

    private static ConcurrentMap<String,Object> map = new ConcurrentHashMap<>();

    private SingletonManager(){

    }
    public static void register(String key,Object instance){
        if(!map.containsKey(key)){
            map.put(key,instance);
        }
    }

    public static Object get(String key){
        return map.get(key);
    }
}
