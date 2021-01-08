package com.tianhe.framework.netty.online.pool;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.WriteBufferWaterMark;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.net.InetAddress;

/**
 * Created by tianhe on 2019/10/11.
 */
@Slf4j
public class NettyServer {

    private ServerChannelInitializer serverChannelInitializer;

    private EventLoopGroup bossGroup;

    private EventLoopGroup workerGroup;

    public NettyServer(ServerChannelInitializer serverChannelInitializer){
        this.serverChannelInitializer = serverChannelInitializer;
    }

    @PostConstruct
    public void start() {
        log.info("=================================================");
        log.info("netty server启动");
        int threads = Runtime.getRuntime().availableProcessors();
        bossGroup = new NioEventLoopGroup(threads);
        workerGroup = new NioEventLoopGroup(threads);
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 1024)
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    .childOption(ChannelOption.TCP_NODELAY, true)
                    .childOption(ChannelOption.SO_SNDBUF, 153600)
                    .childOption(ChannelOption.SO_RCVBUF, 153600)
//                    启用netty流量整型
                    .childOption(ChannelOption.WRITE_BUFFER_WATER_MARK, new WriteBufferWaterMark(1048576, 67108864))
                    .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 100)
                    .option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT)
                    .childHandler(serverChannelInitializer);
            ChannelFuture channelFuture = serverBootstrap.bind(8080).sync();
            channelFuture.channel().closeFuture().sync();
            log.info("netty server启动，url=" + InetAddress.getLocalHost().getHostAddress() + ":" + 8080);
            log.info("=================================================");
        } catch (Exception e) {
            log.error("netty server启动失败，异常信息={}", e);
            log.info("=================================================");
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    @PreDestroy
    public void stop() {
        log.info("=================================================");
        log.error("netty server服务关闭");
        try {
            if (bossGroup != null) {
                bossGroup.shutdownGracefully().await();
            }
            if (workerGroup != null) {
                workerGroup.shutdownGracefully().await();
            }
        } catch (InterruptedException e) {
            log.info("=================================================");
            log.error("netty server关闭失败，异常信息={}", e);
            throw new RuntimeException("netty server关闭失败");
        }
    }
}
