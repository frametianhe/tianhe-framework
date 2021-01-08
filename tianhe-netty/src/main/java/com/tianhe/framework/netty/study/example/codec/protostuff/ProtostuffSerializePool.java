
package com.tianhe.framework.netty.study.example.codec.protostuff;

import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

/**
 * ProtostuffSerializePool.protostuff序列化池
 * @author he.tian
 */
public class ProtostuffSerializePool {

    private static volatile ProtostuffSerializePool poolFactory;

    private GenericObjectPool<ProtostuffSerialize> protostuffPool;

    private ProtostuffSerializePool() {
        protostuffPool = new GenericObjectPool<>(new ProtostuffSerializeFactory());
    }

    public ProtostuffSerializePool(final int maxTotal, final int minIdle, final long maxWaitMillis, final long minEvictableIdleTimeMillis) {
        protostuffPool = new GenericObjectPool<>(new ProtostuffSerializeFactory());
        GenericObjectPoolConfig config = new GenericObjectPoolConfig();
        config.setMaxTotal(maxTotal);
        config.setMinIdle(minIdle);
        config.setMaxWaitMillis(maxWaitMillis);
        config.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        protostuffPool.setConfig(config);
    }

    public static ProtostuffSerializePool getProtostuffPoolInstance() {
        if (poolFactory == null) {
            synchronized (ProtostuffSerializePool.class) {
                if (poolFactory == null) {
                    poolFactory = new ProtostuffSerializePool();
                }
            }
        }
        return poolFactory;
    }

    public ProtostuffSerialize borrow() {
        try {
            return getProtostuffPool().borrowObject();
        } catch (final Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public void restore(final ProtostuffSerialize object) {
        getProtostuffPool().returnObject(object);
    }

    public GenericObjectPool<ProtostuffSerialize> getProtostuffPool() {
        return protostuffPool;
    }
}

