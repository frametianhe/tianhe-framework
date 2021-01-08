package com.tianhe.framework.netty.study.example.localecho;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.local.LocalAddress;
import io.netty.channel.local.LocalChannel;
import io.netty.channel.local.LocalServerChannel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by weifeng.jiang on 2018-07-22 17:37.
 */
public class LocalEcho {

    static final String PORT = "8080";

    public static void main(String[] args) throws Exception{
        final LocalAddress addr = new LocalAddress(PORT);
        EventLoopGroup serverGroup = new NioEventLoopGroup();
        EventLoopGroup clientGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap sb = new ServerBootstrap();
            sb.group(serverGroup)
                    .channel(LocalServerChannel.class)
                    .handler(new ChannelInitializer<LocalServerChannel>() {
                        @Override
                        protected void initChannel(LocalServerChannel ch) throws Exception {
                            ch.pipeline().addLast(new LoggingHandler(LogLevel.INFO));
                        }
                    }).childHandler(new ChannelInitializer<LocalChannel>() {
                @Override
                protected void initChannel(LocalChannel ch) throws Exception {
                    ch.pipeline().addLast(new LoggingHandler(LogLevel.INFO),
                            new LocalechoServerhandler());
                }
            });

            Bootstrap cb = new Bootstrap();
            cb.group(clientGroup)
                    .channel(LocalChannel.class)
                    .handler(new ChannelInitializer<LocalChannel>() {
                        @Override
                        protected void initChannel(LocalChannel ch) throws Exception {
                            ch.pipeline().addLast(new LoggingHandler(LogLevel.INFO),
                                    new LocalEchoClientHandler());
                        }
                    });
            sb.bind(addr).sync();
            Channel ch = cb.connect(addr).sync().channel();
            System.out.println("输入信息，按quit退出");
            ChannelFuture lastWriteFuture = null;
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            for (;;){
                String line = in.readLine();
                if(line == null || "quit".equalsIgnoreCase(line)){
                    break;
                }
                lastWriteFuture = ch.writeAndFlush(line);
            }
            if(lastWriteFuture != null){
                lastWriteFuture.awaitUninterruptibly();
            }
        }finally {
            serverGroup.shutdownGracefully();
            clientGroup.shutdownGracefully();
        }
    }
}
