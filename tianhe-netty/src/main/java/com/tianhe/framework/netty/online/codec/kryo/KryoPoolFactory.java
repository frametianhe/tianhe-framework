package com.tianhe.framework.netty.online.codec.kryo;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.pool.KryoFactory;
import com.esotericsoftware.kryo.pool.KryoPool;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.objenesis.strategy.StdInstantiatorStrategy;

/**
 * Created by tianhe on 2019/10/11.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class KryoPoolFactory {

    private static volatile KryoPoolFactory poolFactory;

    private KryoFactory factory = new KryoFactory() {
        @Override
        public Kryo create() {
            Kryo kryo = new Kryo();
            kryo.setReferences(false);
            kryo.register(Request.class);
            kryo.setInstantiatorStrategy(new Kryo.DefaultInstantiatorStrategy(new StdInstantiatorStrategy()));
            return kryo;
        }
    };

    private KryoPool pool = new KryoPool.Builder(factory).build();

    public static KryoPool getKryoPoolInstance() {
        if (poolFactory == null) {
            synchronized (KryoPoolFactory.class) {
                if (poolFactory == null) {
                    poolFactory = new KryoPoolFactory();
                }
            }
        }
        return poolFactory.getPool();
    }

    public KryoPool getPool() {
        return pool;
    }
}
