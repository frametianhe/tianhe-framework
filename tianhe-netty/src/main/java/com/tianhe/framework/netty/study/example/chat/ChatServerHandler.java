package com.tianhe.framework.netty.study.example.chat;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.ssl.SslHandler;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * Created by weifeng.jiang on 2018-07-22 18:29.
 */
public class ChatServerHandler extends SimpleChannelInboundHandler<String> {

    static final ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    public void channelActive(final ChannelHandlerContext ctx) throws Exception {
        ctx.pipeline().get(SslHandler.class).handshakeFuture().addListener(new GenericFutureListener<Future<? super Channel>>() {
            @Override
            public void operationComplete(Future<? super Channel> future) throws Exception {
                ctx.writeAndFlush("欢迎 127.0.0.1:9080 认证聊天服务");
                ctx.writeAndFlush("你的会话被 "+ctx.pipeline().get(SslHandler.class).engine().getSession().getCipherSuite()+" cipher suite保护");
                channels.add(ctx.channel());
            }
        });
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        for (Channel c:channels){
            if(c!= ctx.channel()){
                c.writeAndFlush("["+ctx.channel().remoteAddress()+"] "+msg+"\n");
            }else {
                c.writeAndFlush("[you] "+msg+"\n");
            }
        }
        if("bye".equals(msg.toLowerCase())){
            ctx.close();
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
