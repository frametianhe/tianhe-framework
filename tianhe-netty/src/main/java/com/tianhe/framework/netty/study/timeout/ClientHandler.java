package com.tianhe.framework.netty.study.timeout;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.concurrent.TimeUnit;

/**
 * @author: he.tian
 * @time: 2018-07-31 13:57
 */
public class ClientHandler extends ChannelInboundHandlerAdapter{

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("client 接收到了服务器返回的信息，"+msg);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        //        模拟写超时
        TimeUnit.SECONDS.sleep(10);
        ctx.writeAndFlush("client 我是客户端");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        System.out.println("使用的是IdleStateHandler读取超时了会进入这个方法=============");
    }
}
