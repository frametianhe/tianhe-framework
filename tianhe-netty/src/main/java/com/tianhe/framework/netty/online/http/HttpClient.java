package com.tianhe.framework.netty.online.http;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestEncoder;
import io.netty.handler.codec.http.HttpResponseDecoder;

/**
 * Created by tianhe on 2019/12/13.
 */
public class HttpClient {

    public static void main(String[] args) throws InterruptedException {
        NioEventLoopGroup group = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(group)
                .option(ChannelOption.SO_KEEPALIVE,true)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ChannelPipeline pipeline = ch.pipeline();
//                        pipeline.addLast(new HttpClientCodec());
//                        pipeline.addLast(new HttpObjectAggregator(1024 * 1024));
                        pipeline.addLast(new HttpResponseDecoder());
                        pipeline.addLast(new HttpObjectAggregator(1024 * 1024));
                        pipeline.addLast(new HttpRequestEncoder());
                        pipeline.addLast(new HttpClientHandler());
                    }
                });
        ChannelFuture channelFuture = bootstrap.connect("localhost", 8090).sync();
        channelFuture.channel().closeFuture().sync();
        group.shutdownGracefully();
    }
}
