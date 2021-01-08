package com.tianhe.framework.netty.study.example.echo;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * Created by weifeng.jiang on 2018-07-21 23:42.
 */
public class EchoClienthandler extends ChannelInboundHandlerAdapter {

    private final ByteBuf firstMessage;

    public EchoClienthandler(){
        firstMessage = Unpooled.buffer(1024);
        for (int i=0;i<firstMessage.capacity();i++){
            firstMessage.writeByte((byte)i);
        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(firstMessage);
        System.out.println("发送========"+firstMessage.toString());
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ctx.write(msg);
        System.out.println("服务端返回================"+msg.toString());
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
