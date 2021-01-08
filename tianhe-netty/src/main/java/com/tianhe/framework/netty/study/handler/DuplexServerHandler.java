package com.tianhe.framework.netty.study.handler;

import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;

/**
 * @author: he.tian
 * @time: 2018-07-31 16:46
 */
public class DuplexServerHandler extends ChannelDuplexHandler{

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("server 接受到客户端发来的消息，"+msg);
        ctx.writeAndFlush("server 回复客户端");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
