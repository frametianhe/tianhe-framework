
package com.tianhe.framework.netty.study.example.codec;

import io.netty.buffer.ByteBuf;

import java.io.IOException;

/**
 * MessageCodecService. 消息编码服务
 * @author he.tian
 */
public interface MessageCodecService {

    /**
     * 消息定长.
     */
    int MESSAGE_LENGTH = 4;

    /**
     * netty 将java对象转成byteBuf.
     *
     * @param out     输出byteBuf
     * @param message 对象信息
     * @throws IOException io异常
     */
    void encode(ByteBuf out, Object message) throws IOException;

    /**
     * netty 将byteBuf转成java对象.
     *
     * @param body byte数组
     * @return Object
     * @throws IOException io异常
     */
    Object decode(byte[] body) throws IOException;
}
