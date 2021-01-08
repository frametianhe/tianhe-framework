
package com.tianhe.framework.netty.study.example.codec.hession;

import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;

/**
 * HessianSerializeFactory.
 * @author he.tian
 */
public class HessianSerializeFactory extends BasePooledObjectFactory<HessianSerialize> {

    @Override
    public HessianSerialize create() {
        return createHessian();
    }

    @Override
    public PooledObject<HessianSerialize> wrap(final HessianSerialize hessian) {
        return new DefaultPooledObject<>(hessian);
    }

    private HessianSerialize createHessian() {
        return new HessianSerialize();
    }
}

