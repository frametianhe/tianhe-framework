package com.tianhe.framework.netty.study.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author: he.tian
 * @time: 2018-07-31 13:57
 */
public class SimpleClientHandler extends SimpleChannelInboundHandler{

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush("client 我是客户端");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("client 接收到服务端的反馈，"+msg);
    }
}
