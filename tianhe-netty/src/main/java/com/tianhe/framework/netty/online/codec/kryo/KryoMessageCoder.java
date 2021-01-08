package com.tianhe.framework.netty.online.codec.kryo;

import com.esotericsoftware.kryo.pool.KryoPool;
import com.google.common.io.Closer;
import io.netty.buffer.ByteBuf;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Created by tianhe on 2019/10/11.
 */
public class KryoMessageCoder implements MessageCoder {

    private KryoPool pool;

    public KryoMessageCoder(final KryoPool pool) {
        this.pool = pool;
    }

    @Override
    public void encode(ByteBuf out, Object msg) throws IOException {
        Closer closer = Closer.create();
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            closer.register(byteArrayOutputStream);
            KryoSerialize kryoSerialize = new KryoSerialize(pool);
            kryoSerialize.serialize(byteArrayOutputStream, msg);
            byte[] body = byteArrayOutputStream.toByteArray();
            int dataLength = body.length;
            out.writeInt(dataLength);
            out.writeBytes(body);
        } finally {
            closer.close();
        }
    }

    @Override
    public Object decode(byte[] body) throws IOException {
        Closer closer = Closer.create();
        try {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(body);
            closer.register(byteArrayInputStream);
            KryoSerialize kryoSerialize = new KryoSerialize(pool);
            return kryoSerialize.deserialize(byteArrayInputStream);
        } finally {
            closer.close();
        }
    }
}
