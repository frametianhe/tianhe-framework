package com.tianhe.framework.netty.online.codec.kryo;

import io.netty.buffer.ByteBuf;

import java.io.IOException;

/**
 * Created by tianhe on 2019/10/11.
 */
public interface MessageCoder {

    int MSG_LENGTH = 4;

    void encode(ByteBuf out, Object msg) throws IOException;

    Object decode(byte[] body) throws IOException;
}
