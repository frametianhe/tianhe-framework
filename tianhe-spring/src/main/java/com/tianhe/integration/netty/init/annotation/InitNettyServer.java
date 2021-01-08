package com.tianhe.integration.netty.init.annotation;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author: he.tian
 * @time: 2018-08-13 15:24
 */
@Service
@Slf4j
public class InitNettyServer {

    private static final int PORT = 8090;

    @Setter
    private EventLoopGroup bossGroup;

    @Setter
    private EventLoopGroup workerGroup;

    @Setter
    private ServerBootstrap serverBootstrap;

    @Autowired
    private InitServerChannelInitializer channelInitializer;

    //类似与构造方法，初始化这个bean的时候会加载这个方法，<bean>标签指定init方法
    @PostConstruct
    public void start() {

        setBossGroup(new NioEventLoopGroup());
        setWorkerGroup(new NioEventLoopGroup());
        setServerBootstrap(new ServerBootstrap());

        try {
            serverBootstrap.group(bossGroup,workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(channelInitializer).option(ChannelOption.SO_BACKLOG,1024)
                    .option(ChannelOption.SO_KEEPALIVE,true);
            ChannelFuture channelFuture = serverBootstrap.bind(PORT);
            channelFuture.channel().closeFuture().sync();
        }catch (Exception e) {
            log.error("启动netty server失败======"+e);
        }
    }

    //bean销毁之前会执行这个方法，<bean>标签指定destory方法，或者实现org.springframework.beans.factory.DisposableBean接口重新destory方法
    @PreDestroy
    public void shutdown(){
        bossGroup.shutdownGracefully();
        workerGroup.shutdownGracefully();
    }
}
