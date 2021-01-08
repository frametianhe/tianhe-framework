
package com.tianhe.framework.netty.study.example.codec.protostuff;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.Schema;
import com.tianhe.framework.netty.study.example.User;
import com.tianhe.framework.netty.study.example.codec.NettyTransferSerialize;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * ProtostuffSerialize.protostuff序列化
 * @author he.tian
 */
public class ProtostuffSerialize implements NettyTransferSerialize {

    private static SchemaCache cachedSchema = SchemaCache.getInstance();

    private static Objenesis objenesis = new ObjenesisStd(true);

    private static <T> Schema<T> getSchema(final Class<T> cls) {
        return (Schema<T>) cachedSchema.get(cls);
    }

    @Override
    public Object deserialize(final InputStream input) {
        try {
            User message = objenesis.newInstance(User.class);
            Schema<User> schema = getSchema(User.class);
            ProtostuffIOUtil.mergeFrom(input, message, schema);
            return message;
        } catch (Exception e) {
            throw new IllegalStateException(e.getMessage(), e);
        }
    }

    @Override
    public void serialize(final OutputStream output, final Object object) {
        Class cls = object.getClass();
        LinkedBuffer buffer = LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE);
        try {
            Schema schema = getSchema(cls);
            ProtostuffIOUtil.writeTo(output, object, schema, buffer);
        } catch (Exception e) {
            throw new IllegalStateException(e.getMessage(), e);
        } finally {
            buffer.clear();
        }
    }
}

