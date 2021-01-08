package com.tianhe.framework.netty.study.example.updatetime;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

import java.util.concurrent.TimeUnit;

/**
 * Created by weifeng.jiang on 2018-07-22 19:40.
 */
@ChannelHandler.Sharable
public class UptimeClientHandler extends SimpleChannelInboundHandler<Object> {

    long startTime = -1;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        if(startTime <0){
            startTime = System.currentTimeMillis();
        }
        System.out.println("链接："+ctx.channel().remoteAddress());
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {

    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if(!(evt instanceof IdleStateEvent)){
            return;
        }
        IdleStateEvent e = (IdleStateEvent) evt;
        if(e.state() == IdleState.READER_IDLE){
            println("失去链接");
            ctx.close();
        }
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        println("失去链接："+ctx.channel().remoteAddress());
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        println("休眠："+UptimeClient.RECONNECT_DELAY+'s');
        ctx.channel().eventLoop().schedule(new Runnable() {
            @Override
            public void run() {
                println("重新链接："+UptimeClient.HOST+":"+UptimeClient.PORT);
                UptimeClient.connect();
            }
        },UptimeClient.RECONNECT_DELAY, TimeUnit.SECONDS);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    void println(String msg){
        if(startTime<0){
            System.out.println("服务关闭："+msg);
        }else{
            System.out.println("更新时间："+((System.currentTimeMillis()-startTime) / 1000)+ " "+ msg);
        }
    }
}
