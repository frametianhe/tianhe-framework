package com.tianhe.framework.netty.online.pool;

import com.tianhe.framework.zookeeper.curator.CuratorFactory;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.pool.AbstractChannelPoolMap;
import io.netty.channel.pool.ChannelHealthChecker;
import io.netty.channel.pool.FixedChannelPool;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.timeout.IdleStateHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.concurrent.TimeUnit;

/**
 * Created by tianhe on 2019/10/11.
 */
@Slf4j
public class NettyClient{

    private EventLoopGroup workerGroup;

    private ClientHandlerInitializer clientHandlerInitializer;

    private Bootstrap bootstrap;

    private AbstractChannelPoolMap<InetSocketAddress, FixedChannelPool> clientChannelPool;

    private static final String THREAD_PERFIX = "_";

    private CuratorFramework curatorFramework;

    public NettyClient(){
        this.curatorFramework = CuratorFactory.getInstance();
    }

    @PostConstruct
    public void start() {
        log.info("=================================================================");
        log.info("netty client启动");
        try {
            int threads = Runtime.getRuntime().availableProcessors();
            workerGroup = new NioEventLoopGroup(threads, new NamedThreadFactory(getThreadPerfix("client-selector"), threads));
            bootstrap = new Bootstrap();
            bootstrap.group(workerGroup).channel(NioSocketChannel.class);
            bootstrap.option(ChannelOption.TCP_NODELAY, true)
                    .option(ChannelOption.SO_KEEPALIVE, true)
                    .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 3000)
                    .option(ChannelOption.SO_SNDBUF, 153600)
                    .option(ChannelOption.SO_RCVBUF, 153600)
                    .option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);
            clientChannelPool = new AbstractChannelPoolMap<InetSocketAddress, FixedChannelPool>() {
                @Override
                protected FixedChannelPool newPool(InetSocketAddress key) {
                    return new FixedChannelPool(bootstrap.remoteAddress(key), new DefaultChannelPoolHandler() {
                        @Override
                        public void channelCreated(Channel ch) throws Exception {
                            super.channelCreated(ch);
                            ChannelPipeline pipeline = ch.pipeline();
                            pipeline.addLast(workerGroup, new IdleStateHandler(3000, 3000, 3000));
                            pipeline.addLast(workerGroup, new NettyClientHandler());
                        }
                    }, ChannelHealthChecker.ACTIVE,
                            FixedChannelPool.AcquireTimeoutAction.FAIL,
                            3000,
                            10,
                            3000,
                            false);
                }
            };
            log.info("=================================================================");
        } catch (Exception e) {
            log.error("netty client启动失败，异常信息={}", e);
            log.info("=================================================================");
        }
    }

    @PreDestroy
    public void stop() {
        log.error("netty client 服务关闭");
        log.info("=================================================================");
        if (clientChannelPool != null) {
            clientChannelPool.close();
        }
        if (workerGroup != null) {
            workerGroup.shutdownGracefully();
        }
    }

    public Channel getNewChannel(SocketAddress address) {
        Channel channel;
        ChannelFuture channelFuture = bootstrap.connect(address);
        try {
            channelFuture.await(3000, TimeUnit.MILLISECONDS);
            if (channelFuture.isCancelled()) {
                log.error("连接取消，不能连接netty服务器");
                throw new RuntimeException("连接取消，不能连接netty服务器");
            } else if (!channelFuture.isSuccess()) {
                log.error("连接失败，不能连接netty服务器");
                throw new RuntimeException("连接失败，不能连接netty服务器");
            } else {
                channel = channelFuture.channel();
            }
        } catch (InterruptedException e) {
            log.error("不能连接netty服务器，异常信息={}", e);
            throw new RuntimeException("不能连接netty服务器");
        }
        return channel;
    }

    private String getThreadPerfix(String threaPrefix) {
        return threaPrefix + THREAD_PERFIX;
    }
}
