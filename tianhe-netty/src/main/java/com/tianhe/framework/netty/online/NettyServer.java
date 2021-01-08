package com.tianhe.framework.netty.online;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author: he.tian
 * @time: 2018-10-15 14:46
 */
public class NettyServer{

    private Logger logger = LoggerFactory.getLogger(getClass());

    private ServerChannelInitializer serverChannelInitializer;

    private EventLoopGroup bossGroup;

    private EventLoopGroup workerGroup;

    public NettyServer(ServerChannelInitializer serverChannelInitializer){
        this.serverChannelInitializer = serverChannelInitializer;
    }

    @PostConstruct
    public void start(){
        logger.info("=================================================================");
        logger.info("netty server 启动");
        int threads = Runtime.getRuntime().availableProcessors();
         bossGroup = new NioEventLoopGroup(threads);
         workerGroup = new NioEventLoopGroup(threads);
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup,workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_KEEPALIVE,true)
                    .option(ChannelOption.SO_BACKLOG,100)
                    .option(ChannelOption.CONNECT_TIMEOUT_MILLIS,100)
                    .option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(serverChannelInitializer);
            logger.info("=================================================================");
            ChannelFuture channelFuture = serverBootstrap.bind(8080).sync();
            channelFuture.channel().closeFuture().sync();
        } catch (Exception e) {
            logger.info("=================================================================");
            logger.error("启动失败",e);
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    @PreDestroy
    public void stop(){
        logger.info("=================================================================");
        logger.info("netty server 服务关闭");
        try {
            if(bossGroup != null){
                bossGroup.shutdownGracefully().await();
            }
            if(workerGroup != null){
                workerGroup.shutdownGracefully().await();
            }
        } catch (InterruptedException e) {
            logger.info("=================================================================");
            logger.error("netty server 关闭失败",e);
            throw new RuntimeException("netty server关闭失败");
        }
    }
}
