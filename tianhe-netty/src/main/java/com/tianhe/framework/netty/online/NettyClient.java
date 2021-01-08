package com.tianhe.framework.netty.online;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.concurrent.TimeUnit;

/**
 * @author: he.tian
 * @time: 2018-11-01 17:23
 */
public class NettyClient{

    private Logger logger = LoggerFactory.getLogger(getClass());

    private EventLoopGroup workerGroup;

    private ClientHandherInitializer clientHandherInitializer;

    private Channel channel;

    private Bootstrap bootstrap;

    public NettyClient(ClientHandherInitializer clientHandherInitializer){
        this.clientHandherInitializer = clientHandherInitializer;
    }

    @PostConstruct
    public void start(){
        logger.info("=================================================================");
        logger.info("netty client 启动");
        try {
            workerGroup = new NioEventLoopGroup(Runtime.getRuntime().availableProcessors());
            bootstrap = new Bootstrap();
            bootstrap.group(workerGroup)
            .channel(NioSocketChannel.class);
            bootstrap.option(ChannelOption.SO_BACKLOG,1024)
                    .option(ChannelOption.CONNECT_TIMEOUT_MILLIS,5)
                    .option(ChannelOption.SO_KEEPALIVE,true)
                    .option(ChannelOption.TCP_NODELAY,true)
                    .option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .handler(clientHandherInitializer);
            doConnect();
        } catch (Exception e) {
            logger.info("=================================================================");
            logger.info("netty client 启动失败",e);
        }
    }

    @PreDestroy
    public void stop() throws Exception {
        logger.info("=================================================================");
        logger.info("netty client 服务关闭");
        if(workerGroup != null){
            workerGroup.shutdownGracefully();
        }
    }

    public void doConnect(){
        if(channel != null && channel.isActive()){
            return;
        }
        ChannelFuture future = bootstrap.connect("localhost", 8080);
        logger.info("=================================================================");
        logger.info("netty client 连接 netty server,url=localhost,port=8080");
        future.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture channelFuture) throws Exception {
                if(channelFuture.isSuccess()){
                    channel = channelFuture.channel();
                    logger.info("=================================================================");
                    logger.info("netty client 连接 netty server 成功,url=localhost,port=8080");
                }else{
//                    TODO 现在处理，连接不上一直重试，后续优化超过最大重试次数报警
                    logger.info("=================================================================");
                    logger.info("netty client 连接 netty server 失败，5s后重试,url=localhost,port=8080");
                    channelFuture.channel().eventLoop().schedule(NettyClient.this::doConnect,5, TimeUnit.SECONDS);
                }
            }
        });
    }
}
