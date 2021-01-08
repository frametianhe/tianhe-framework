package com.tianhe.framework.netty.study.example.codec.httphelloworld;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.SelfSignedCertificate;

/**
 * @author: he.tian
 * @time: 2018-08-09 19:33
 */
public class HttpServer {

    private static final boolean SSL = true;

    /**
     * 浏览器输入 http://127.0.0.1:8090、https://127.0.0.1:8090 都可访问
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception{
        final SslContext sslContext;
        if(SSL){
            SelfSignedCertificate selfSignedCertificate = new SelfSignedCertificate();
            sslContext = SslContextBuilder.forServer(selfSignedCertificate.certificate(),selfSignedCertificate.privateKey()).build();
        }else{
            sslContext = null;
        }
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup  = new NioEventLoopGroup();
        ServerBootstrap b = new ServerBootstrap();
        b.option(ChannelOption.SO_BACKLOG,1024);
        b.group(bossGroup,workerGroup)
                .channel(NioServerSocketChannel.class)
                .handler(new LoggingHandler(LogLevel.INFO))
                .childHandler(new HttpServerChannelInit(sslContext));
        Channel channel = b.bind(8090).sync().channel();
        channel.closeFuture().sync();

//        在finally语句块中写
        bossGroup.shutdownGracefully();
        workerGroup.shutdownGracefully();
    }
}
