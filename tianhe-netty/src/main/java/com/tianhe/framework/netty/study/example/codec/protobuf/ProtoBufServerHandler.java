package com.tianhe.framework.netty.study.example.codec.protobuf;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author: he.tian
 * @time: 2018-08-02 12:28
 */
public class ProtoBufServerHandler extends ChannelInboundHandlerAdapter{

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Message.MessageBase msgBase = (Message.MessageBase) msg;
        System.out.println(msgBase.getData());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
