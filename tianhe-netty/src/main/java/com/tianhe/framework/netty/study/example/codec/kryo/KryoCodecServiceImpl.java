
package com.tianhe.framework.netty.study.example.codec.kryo;

import com.esotericsoftware.kryo.pool.KryoPool;
import com.google.common.io.Closer;
import com.tianhe.framework.netty.study.example.codec.MessageCodecService;
import io.netty.buffer.ByteBuf;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * KryoCodecServiceImpl. kyro解析器实现
 *
 * @author he.tian
 */
public class KryoCodecServiceImpl implements MessageCodecService {
    
    private KryoPool pool;

    public KryoCodecServiceImpl(final KryoPool pool) {
        this.pool = pool;
    }

    @Override
    public void encode(final ByteBuf out, final Object message) throws IOException {
        Closer closer = Closer.create();
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            closer.register(byteArrayOutputStream);
            KryoSerialize kryoSerialization = new KryoSerialize(pool);
            kryoSerialization.serialize(byteArrayOutputStream, message);
            byte[] body = byteArrayOutputStream.toByteArray();
            int dataLength = body.length;
            out.writeInt(dataLength);
            out.writeBytes(body);
        } finally {
            closer.close();
        }
    }

    @Override
    public Object decode(final byte[] body) throws IOException {
        Closer closer = Closer.create();
        try {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(body);
            closer.register(byteArrayInputStream);
            KryoSerialize kryoSerialization = new KryoSerialize(pool);
            return kryoSerialization.deserialize(byteArrayInputStream);
        } finally {
            closer.close();
        }
    }
}
