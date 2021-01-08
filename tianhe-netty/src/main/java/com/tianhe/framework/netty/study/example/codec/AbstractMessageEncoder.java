
package com.tianhe.framework.netty.study.example.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * AbstractMessageEncoder.消息编码抽象类
 * @author he.tian
 */
public abstract class AbstractMessageEncoder extends MessageToByteEncoder<Object> {

    private MessageCodecService util;

    public AbstractMessageEncoder(final MessageCodecService util) {
        this.util = util;
    }

    @Override
    protected void encode(final ChannelHandlerContext ctx, final Object msg, final ByteBuf out) throws Exception {
        util.encode(out, msg);
    }
}

