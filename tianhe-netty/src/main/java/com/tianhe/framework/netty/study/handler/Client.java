package com.tianhe.framework.netty.study.handler;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * @author: he.tian
 * @time: 2018-07-31 13:57
 */
public class Client {

    public static void main(String[] args) throws InterruptedException {
//        基于nio选择器的多线程事件组
        EventLoopGroup loopGroup = new NioEventLoopGroup();
        Bootstrap b = new Bootstrap();
        b.group(loopGroup)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ChannelPipeline pipeline = ch.pipeline();
                        pipeline.addLast(new LoggingHandler(LogLevel.INFO));
                        pipeline.addLast(new StringEncoder());
//                      加了encoder，没有加decoder，可以向服务端发送信息，但是接收不到服务端返回的信息
                        pipeline.addLast(new StringDecoder());
                        pipeline.addLast(new InboundClientHandler());
//                        pipeline.addLast(new SimpleClientHandler());
//                        pipeline.addLast(new DuplexClientHandler());
                    }
                });
        ChannelFuture channelFuture = b.connect("127.0.0.1", 8088).sync();
        channelFuture.channel().closeFuture().sync();
        loopGroup.shutdownGracefully();//事件组优雅关闭，这个在finally中调用
    }
}
