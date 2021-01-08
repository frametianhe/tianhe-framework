
package com.tianhe.framework.netty.study.example.codec;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * NettyTransferSerialize.netty转换序列化
 * @author he.tian
 */
public interface NettyTransferSerialize {

    /**
     * netty 将object序列化成 OutputStream.
     *
     * @param output OutputStream
     * @param object 对象
     * @throws IOException io异常
     */
    void serialize(OutputStream output, Object object) throws IOException;

    /**
     * netty 将 InputStream 反序列成对象.
     *
     * @param input 输出流
     * @return object
     * @throws IOException io异常
     */
    Object deserialize(InputStream input) throws IOException;
}
