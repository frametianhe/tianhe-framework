package com.tianhe.framework.netty.study;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author: he.tian
 * @time: 2018-08-07 21:23
 */
public class Server {

    public static void main(String[] args) throws InterruptedException {
        EventLoopGroup bossGroup =  new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ServerChannelInitializer())
                    .option(ChannelOption.SO_BACKLOG,1024)
                    .option(ChannelOption.SO_KEEPALIVE,true);
            ChannelFuture channelFuture = b.bind(8090).sync();
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
           e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    private static boolean userEpoll(){
        return NettyUtil.isLinuxPlatform();
    }
}
