package com.tianhe.framework.netty.study.timeout;

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
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

/**
 * @author: he.tian
 * @time: 2018-07-31 13:57
 */
public class TimeoutCient {

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
//                        当在一定时间内没有读取数据时，引发ReadTimeoutException
//                        pipeline.addLast(new ReadTimeoutHandler(20));
                        pipeline.addLast(new IdleStateHandler(20,0,0,TimeUnit.SECONDS));
                        pipeline.addLast(new StringEncoder());
                        pipeline.addLast(new StringDecoder());
                        pipeline.addLast(new ClientHandler());
                    }
                });
        ChannelFuture channelFuture = b.connect("127.0.0.1", 8088).sync();
        channelFuture.channel().closeFuture().sync();
        loopGroup.shutdownGracefully();//事件组优雅关闭，这个在finally中调用
    }
}
