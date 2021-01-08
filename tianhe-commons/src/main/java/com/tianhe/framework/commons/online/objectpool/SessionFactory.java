package com.tianhe.framework.commons.online.objectpool;

import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;

/**
 * weifeng.jiang 2018-07-20 20:02
 */
public class SessionFactory extends BasePooledObjectFactory<Session>{

    @Override
    public Session create() throws Exception {
        return new Session();
    }

    @Override
    public PooledObject<Session> wrap(Session session) {
        return new DefaultPooledObject<>(session);
    }
}
