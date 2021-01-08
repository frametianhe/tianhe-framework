
package com.tianhe.framework.netty.study.example.codec.hession;

import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

/**
 * HessianSerializePool. hessian序列化池
 * @author he.tian
 */
public class HessianSerializePool {

    private static volatile HessianSerializePool poolFactory;

    private GenericObjectPool<HessianSerialize> hessianPool;

    private HessianSerializePool() {
        hessianPool = new GenericObjectPool<>(new HessianSerializeFactory());
    }

    public HessianSerializePool(final int maxTotal, final int minIdle, final long maxWaitMillis, final long minEvictableIdleTimeMillis) {
        hessianPool = new GenericObjectPool<>(new HessianSerializeFactory());
        GenericObjectPoolConfig config = new GenericObjectPoolConfig();
        config.setMaxTotal(maxTotal);
        config.setMinIdle(minIdle);
        config.setMaxWaitMillis(maxWaitMillis);
        config.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        hessianPool.setConfig(config);
    }

    public static HessianSerializePool getHessianPoolInstance() {
        if (poolFactory == null) {
            synchronized (HessianSerializePool.class) {
                if (poolFactory == null) {
                    poolFactory = new HessianSerializePool();
                }
            }
        }
        return poolFactory;
    }

    public HessianSerialize borrow() {
        try {
            return getHessianPool().borrowObject();
        } catch (final Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public void restore(final HessianSerialize object) {
        getHessianPool().returnObject(object);
    }

    public GenericObjectPool<HessianSerialize> getHessianPool() {
        return hessianPool;
    }
}
