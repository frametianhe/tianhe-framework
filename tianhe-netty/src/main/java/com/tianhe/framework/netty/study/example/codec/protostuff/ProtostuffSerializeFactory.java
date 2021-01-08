
package com.tianhe.framework.netty.study.example.codec.protostuff;

import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;

/**
 * ProtostuffSerializeFactory.protostuff序列化工厂
 * @author he.tian
 */
public class ProtostuffSerializeFactory extends BasePooledObjectFactory<ProtostuffSerialize> {

    @Override
    public ProtostuffSerialize create() {
        return createProtostuff();
    }

    @Override
    public PooledObject<ProtostuffSerialize> wrap(final ProtostuffSerialize protostuffSerialize) {
        return new DefaultPooledObject<>(protostuffSerialize);
    }

    private ProtostuffSerialize createProtostuff() {
        return new ProtostuffSerialize();
    }
}
