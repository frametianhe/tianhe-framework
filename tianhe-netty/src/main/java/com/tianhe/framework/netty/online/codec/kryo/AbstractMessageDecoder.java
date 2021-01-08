package com.tianhe.framework.netty.online.codec.kryo;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * Created by tianhe on 2019/10/11.
 */
@Slf4j
public class AbstractMessageDecoder extends ByteToMessageDecoder {

    private static final int MSG_LENGTH = MessageCoder.MSG_LENGTH;

    private MessageCoder messageCoder;

    public AbstractMessageDecoder(final MessageCoder messageCoder) {
        this.messageCoder = messageCoder;
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        if (in.readableBytes() < AbstractMessageDecoder.MSG_LENGTH) {
            return;
        }
        in.markReaderIndex();
        int msgLength = -in.readInt();
        if (msgLength < 0) {
            ctx.close();
        }
        if (in.readableBytes() < msgLength) {
            in.resetReaderIndex();
        } else {
            byte[] messageBody = new byte[msgLength];
            in.readBytes(messageBody);
            try {
                Object obj = messageCoder.decode(messageBody);
                out.add(obj);
            } catch (IndexOutOfBoundsException ex) {
                log.error("解码异常，异常信息={}", ex);
            }
        }
    }
}
