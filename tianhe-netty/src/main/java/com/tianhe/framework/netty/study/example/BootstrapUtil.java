package com.tianhe.framework.netty.study.example;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @author: he.tian
 * @time: 2018-08-02 10:15
 */
public abstract class BootstrapUtil {

    /**
     * 初始化客户端
     *
     * @param clientChannelInitializer
     */
    public static void initClient(ChannelInitializer clientChannelInitializer) {
        EventLoopGroup loopGroup = null;
        try {
            loopGroup = new NioEventLoopGroup();
            Bootstrap b = new Bootstrap();
            b.group(loopGroup)
                    .channel(NioSocketChannel.class)
                    .handler(clientChannelInitializer)
                    .option(ChannelOption.SO_BACKLOG,1024)
                    .option(ChannelOption.SO_KEEPALIVE,true);
            ChannelFuture channelFuture = b.connect("127.0.0.1", 8090).sync();
            channelFuture.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            loopGroup.shutdownGracefully();
        }
    }

    /**
     * 初始化服务端
     *
     * @param serverChannelInitializer
     */
    public static void initServer(ChannelInitializer serverChannelInitializer) {
        EventLoopGroup bossGroup = null;
        EventLoopGroup workGroup = null;
        try {
            bossGroup = new NioEventLoopGroup();
            workGroup = new NioEventLoopGroup();
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(serverChannelInitializer)
                    .option(ChannelOption.SO_BACKLOG,1024)
                    .option(ChannelOption.SO_KEEPALIVE,true);
            ChannelFuture channelFuture = b.bind(8090).sync();
            channelFuture.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }
}
