package com.tianhe.framework.netty.study.example.codec.httphelloworld;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.HttpServerExpectContinueHandler;
import io.netty.handler.ssl.SslContext;

/**
 * @author: he.tian
 * @time: 2018-08-09 19:20
 */
public class HttpServerChannelInit extends ChannelInitializer<SocketChannel> {

    private SslContext sslContext;

    public HttpServerChannelInit(SslContext sslContext){
        this.sslContext = sslContext;
    }

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        if(sslContext != null){
            pipeline.addLast(sslContext.newHandler(ch.alloc()));
        }
        pipeline.addLast(new HttpServerCodec());
        pipeline.addLast(new HttpServerExpectContinueHandler());
        pipeline.addLast(new HttpServerHandler());
    }
}
