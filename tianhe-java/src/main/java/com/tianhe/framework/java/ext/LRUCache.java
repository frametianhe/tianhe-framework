package com.tianhe.framework.java.ext;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author: he.tian
 * @time: 2019-04-30 15:39
 */
public class LRUCache<K,V> {

    private int cacheSize;
    private LinkedHashMap<K,V> cacheMap;

    public LRUCache(int cacheSize){
        this.cacheSize = cacheSize;
        cacheMap = new LinkedHashMap(16,0.75F,true){
            @Override
            protected boolean removeEldestEntry(Map.Entry eldest) {
                if(cacheSize +1 == cacheMap.size()){
                    return true;
                }else{
                    return false;
                }
            }
        };
    }

    public void put(K key,V value){
        cacheMap.put(key,value);
    }

    public V get(K key){
        return cacheMap.get(key);
    }

    public Collection<Map.Entry<K,V>> getAll(){
        return new ArrayList<Map.Entry<K,V>>(cacheMap.entrySet());
    }

    public static void main(String[] args) {
        LRUCache<String,Integer> cache = new LRUCache(3);
        cache.put("1",1);
        cache.put("2",2);
        cache.put("3",3);
//        for (Map.Entry<String,Integer> entry : cache.getAll()){
//            System.err.println(entry.getKey()+" "+entry.getValue());
//        }
        cache.put("4",4);
        for (Map.Entry<String,Integer> entry : cache.getAll()){
            System.err.println(entry.getKey()+" "+entry.getValue());
        }
    }
}
