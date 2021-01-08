package com.tianhe.framework.netty.study.example.codec.delimiterbased;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author: he.tian
 * @time: 2018-08-01 19:29
 */
public class ClientHandler extends ChannelInboundHandlerAdapter{

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
//        最后没有@最后一段字符不会解析到
        ctx.writeAndFlush(Unpooled.copiedBuffer("asdasd@asdasd@asdasd".getBytes()));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
