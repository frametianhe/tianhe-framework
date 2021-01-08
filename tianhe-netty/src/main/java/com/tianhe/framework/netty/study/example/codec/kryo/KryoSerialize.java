
package com.tianhe.framework.netty.study.example.codec.kryo;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.esotericsoftware.kryo.pool.KryoPool;
import com.tianhe.framework.netty.study.example.codec.NettyTransferSerialize;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * KryoSerialize.kryo序列化
 * @author he.tian
 */
public class KryoSerialize implements NettyTransferSerialize {

    private KryoPool pool;

    public KryoSerialize(final KryoPool pool) {
        this.pool = pool;
    }

    @Override
    public void serialize(final OutputStream output, final Object object) throws IOException {
        Kryo kryo = pool.borrow();
        Output out = new Output(output);
        kryo.writeClassAndObject(out, object);
        out.close();
        output.close();
        pool.release(kryo);
    }

    @Override
    public Object deserialize(final InputStream input) throws IOException {
        Kryo kryo;
        kryo = pool.borrow();
        try (Input in = new Input(input)) {
            return kryo.readClassAndObject(in);
        } finally {
            input.close();
            pool.release(kryo);
        }
    }

}
