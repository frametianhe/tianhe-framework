package com.tianhe.framework.netty.study.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author: he.tian
 * @time: 2018-07-31 16:40
 */
public class SimpleServerHandler extends SimpleChannelInboundHandler {

    @Override
    public void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("server 读取到客户端信息，"+msg);
        ctx.writeAndFlush("发送嘻嘻");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
