package com.tianhe.framework.commons.online.objectpool;

import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

/**
 * weifeng.jiang 2018-07-20 19:59
 */
public class SessionPool {

    private static volatile SessionPool poolFactory;
    private GenericObjectPool<Session> sessionPool;

    private SessionPool(){
        sessionPool = new GenericObjectPool<Session>(new SessionFactory());
    }

    public SessionPool(final int maxTotal,final int minIdle,final long maxWaitMillis,final long minEvictableIdleTimeMillis){
        sessionPool = new GenericObjectPool<Session>(new SessionFactory());
        GenericObjectPoolConfig config = new GenericObjectPoolConfig();
        config.setMaxTotal(maxTotal);
        config.setMinIdle(minIdle);
        config.setMaxWaitMillis(maxWaitMillis);
        config.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        sessionPool.setConfig(config);
    }

    public static SessionPool getSessionPoolInstance(){
        if(poolFactory == null){
            synchronized (SessionPool.class){
                if(poolFactory == null){
                    poolFactory = new SessionPool();
                }
            }
        }
        return poolFactory;
    }

    public Session borrow(){
        try {
            return getSessionPool().borrowObject();
        }catch (final Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public void restore(final Session object){
        getSessionPool().returnObject(object);
    }

    public GenericObjectPool<Session> getSessionPool(){
        return sessionPool;
    }
}
