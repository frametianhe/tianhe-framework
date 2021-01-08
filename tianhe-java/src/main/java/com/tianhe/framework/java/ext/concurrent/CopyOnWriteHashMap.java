package com.tianhe.framework.java.ext.concurrent;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 扩展一个读写分离线程安全的hashMap
 * @author: he.tian
 * @time: 2019-07-25 12:29
 */
public class CopyOnWriteHashMap<K,V> implements Map<K,V>,Cloneable {

    private volatile Map<K,V> internalMap;

    private Lock lock = new ReentrantLock();

    public CopyOnWriteHashMap(){
        internalMap = new HashMap<>();
    }

    public CopyOnWriteHashMap(int initialCapacity){
        internalMap = new HashMap<>(initialCapacity);
    }

    public CopyOnWriteHashMap(Map<K,V> map){
        internalMap = new HashMap<>(map);
    }

    @Override
    public int size() {
        return internalMap.size();
    }

    @Override
    public boolean isEmpty() {
        return internalMap.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return internalMap.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return internalMap.containsValue(value);
    }

    @Override
    public V get(Object key) {
        return internalMap.get(key);
    }

    @Override
    public V put(K key, V value) {
        try {
            lock.lock();
            Map<K,V> newHashMap = new HashMap<>(internalMap);
            V val = newHashMap.put(key,value);
            internalMap = newHashMap;
            return val;
        }finally {
            lock.unlock();
        }
    }

    @Override
    public V remove(Object key) {
        try {
            lock.lock();
            Map<K,V> newHashMap = new HashMap<>(internalMap);
            V val = newHashMap.remove(key);
            internalMap = newHashMap;
            return val;
        }finally {
            lock.unlock();
        }
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        try {
            lock.lock();
            Map<K,V> newHashMap = new HashMap<>(internalMap);
            newHashMap.putAll(m);
            internalMap = newHashMap;
        }finally {
            lock.unlock();
        }
    }

    @Override
    public void clear() {
        try {
            lock.lock();
            internalMap = new HashMap<>();
        }finally {
            lock.unlock();
        }
    }

    @Override
    public Set<K> keySet() {
        return internalMap.keySet();
    }

    @Override
    public Collection<V> values() {
        return internalMap.values();
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return internalMap.entrySet();
    }
}
