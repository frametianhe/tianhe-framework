package com.tianhe.framework.netty.online.codec.kryo;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * Created by tianhe on 2019/10/11.
 */
public class AbstractMessageEncoder extends MessageToByteEncoder<Object> {

    private MessageCoder messageCoder;

    public AbstractMessageEncoder(final MessageCoder messageCoder) {
        this.messageCoder = messageCoder;
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, Object msg, ByteBuf out) throws Exception {
        messageCoder.encode(out, msg);
    }
}
