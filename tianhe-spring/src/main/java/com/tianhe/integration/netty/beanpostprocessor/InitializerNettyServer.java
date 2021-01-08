package com.tianhe.integration.netty.beanpostprocessor;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: he.tian
 * @time: 2018-08-13 15:24
 */
@Service
@Slf4j
public class InitializerNettyServer implements DisposableBean {

    private static final int PORT = 8090;

    @Setter
    private EventLoopGroup bossGroup;

    @Setter
    private EventLoopGroup workerGroup;

    @Setter
    private ServerBootstrap serverBootstrap;

    @Autowired
    private InitializerServerChannelInitializer channelInitializer;

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

    @Override
    public void destroy() throws Exception {
        bossGroup.shutdownGracefully();
        workerGroup.shutdownGracefully();
    }
}
