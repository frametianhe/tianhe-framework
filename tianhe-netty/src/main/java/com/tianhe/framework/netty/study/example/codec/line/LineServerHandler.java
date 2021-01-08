package com.tianhe.framework.netty.study.example.codec.line;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author: he.tian
 * @time: 2018-08-02 12:28
 */
public class LineServerHandler extends ChannelInboundHandlerAdapter{

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("-------------");
        ByteBuf buf = (ByteBuf) msg;
        byte[] arr = new byte[8];
//        直接缓冲区要先把内容从非直接缓冲中读取到本地内存
        buf.readBytes(arr);
        System.out.println(new String(arr));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
