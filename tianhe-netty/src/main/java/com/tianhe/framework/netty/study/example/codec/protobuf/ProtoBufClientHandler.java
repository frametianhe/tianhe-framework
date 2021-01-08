package com.tianhe.framework.netty.study.example.codec.protobuf;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author: he.tian
 * @time: 2018-08-02 12:27
 */
public class ProtoBufClientHandler extends ChannelInboundHandlerAdapter{

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Message.MessageBase.Builder msg = Message.MessageBase.newBuilder();
        msg.setClientId("12312312");
        msg.setCmd(Command.CommandType.AUTH);
        msg.setData("asdasd");
        ctx.writeAndFlush(msg.build());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
