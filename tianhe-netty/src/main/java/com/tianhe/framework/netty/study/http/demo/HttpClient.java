package com.tianhe.framework.netty.study.http.demo;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.*;

import java.io.UnsupportedEncodingException;

/**
 * Created by tianhe on 2019/12/13.
 */
public class HttpClient {

    public static void main(String[] args) throws InterruptedException, UnsupportedEncodingException {
        NioEventLoopGroup group = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(group)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.SO_KEEPALIVE,true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new HttpResponseDecoder());
                        ch.pipeline().addLast(new HttpRequestEncoder());
                        ch.pipeline().addLast(new HttpClientHandler());
                    }
                });
        ChannelFuture channelFuture = bootstrap.connect("localhost", 8090).sync();
        String uri = "http://localhost:8090";
        String msg = "hello";
        DefaultFullHttpRequest request = new DefaultFullHttpRequest(HttpVersion.HTTP_1_1, HttpMethod.GET,
                uri, Unpooled.wrappedBuffer(msg.getBytes("utf-8")));
        request.headers().set(HttpHeaderNames.HOST,"localhost");
        request.headers().set(HttpHeaderNames.CONNECTION,HttpHeaderValues.KEEP_ALIVE);
        request.headers().set(HttpHeaderNames.CONTENT_LENGTH,request.content().readableBytes());
        channelFuture.channel().writeAndFlush(request);
        channelFuture.channel().closeFuture().sync();
        group.shutdownGracefully();
    }
}
