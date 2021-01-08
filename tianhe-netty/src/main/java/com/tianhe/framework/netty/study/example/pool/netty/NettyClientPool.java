package com.tianhe.framework.netty.study.example.pool.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.pool.AbstractChannelPoolMap;
import io.netty.channel.pool.ChannelPoolHandler;
import io.netty.channel.pool.ChannelPoolMap;
import io.netty.channel.pool.FixedChannelPool;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.util.CharsetUtil;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

import java.net.InetSocketAddress;

/**
 * Created by tianhe on 2019/10/1.
 */
public class NettyClientPool {

    //    key为目标host，value为目标host连接池
    public ChannelPoolMap<InetSocketAddress, FixedChannelPool> channelPoolMap = null;

    public NettyClientPool() {
        init();
    }

    public void init() {
        EventLoopGroup group = new NioEventLoopGroup();
        final Bootstrap cb = new Bootstrap();
        cb.group(group).channel(NioSocketChannel.class);
        channelPoolMap = new AbstractChannelPoolMap<InetSocketAddress, FixedChannelPool>() {
            @Override
            protected FixedChannelPool newPool(InetSocketAddress key) {
                return new FixedChannelPool(cb.remoteAddress(key), new ChannelPoolHandler() {
                    @Override
                    public void channelReleased(Channel ch) throws Exception {
                        System.out.println("释放channel");
                    }

                    @Override
                    public void channelAcquired(Channel ch) throws Exception {
                        System.out.println("获得channel");
                    }

                    @Override
                    public void channelCreated(Channel ch) throws Exception {
                        ch.pipeline()
                                .addLast(new LoggingHandler(LogLevel.INFO))
                                .addLast(new StringEncoder(CharsetUtil.UTF_8))
                                .addLast(new ClientHandler());
                    }
                }, 20);
            }
        };
    }

    public void send(InetSocketAddress address, final String msg) {
        if (address == null) {
            throw new IllegalArgumentException();
        }
        final FixedChannelPool pool = this.channelPoolMap.get(address);
        Future<Channel> future = pool.acquire();
        future.addListener(new GenericFutureListener<Future<? super Channel>>() {
            @Override
            public void operationComplete(Future<? super Channel> future) throws Exception {
                if (future.isSuccess()) {
                    Channel channel = (Channel) future.getNow();
                    ChannelFuture channelFuture = channel.writeAndFlush(msg);
                    if (channelFuture != null) {
                        channelFuture.sync();
                    }
                    pool.release(channel);
                }
            }
        });
    }

    public static void main(String[] args) {
        NettyClientPool clientPool = new NettyClientPool();
        InetSocketAddress address = new InetSocketAddress("127.0.0.1", 8080);
        for (int i = 0; i < 100; i++) {
            clientPool.send(address, "你好");
        }
    }
}
