package com.tianhe.framework.netty.study.example.objectecho;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by weifeng.jiang on 2018-07-22 17:54.
 */
public class ObjectEchoClientHandler extends ChannelInboundHandlerAdapter{

    private final List<Integer> firstMessage;

    public ObjectEchoClientHandler(){
        firstMessage = new ArrayList<>(256);
        for (int i=0;i<256;i++){
            firstMessage.add(Integer.valueOf(i));
        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(firstMessage);
        System.out.println("发送："+firstMessage);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ctx.write(msg);
        System.out.println("读取到了："+msg+"，发送："+msg);
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
