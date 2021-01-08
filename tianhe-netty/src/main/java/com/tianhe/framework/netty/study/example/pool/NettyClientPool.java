package com.tianhe.framework.netty.study.example.pool;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.util.CharsetUtil;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by tianhe on 2019/10/1.
 */
public class NettyClientPool {

    ConcurrentMap<String,List<Channel>> channelPool = new ConcurrentHashMap<>(20);

    public NettyClientPool() {
        init();
    }

    public void init() {
        EventLoopGroup group = new NioEventLoopGroup();
        final Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(group)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.TCP_NODELAY,true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ChannelPipeline pipeline = ch.pipeline();
                        pipeline.addLast(new LoggingHandler(LogLevel.INFO));
                        pipeline.addLast(new StringEncoder(CharsetUtil.UTF_8));
                        pipeline.addLast(new ClientHandler());
                    }
                });
        for (int i=0;i<20;i++){
            try {
                ChannelFuture channelFuture = bootstrap.connect("127.0.0.1", 8080).sync();
                List channelList = new ArrayList();
                channelList.add(channelFuture.channel());
                channelPool.put("127.0.0.1",channelList);
                channelFuture.channel().closeFuture().addListener(new ChannelFutureListener() {
                    @Override
                    public void operationComplete(ChannelFuture future) throws Exception {
                        group.shutdownGracefully();
                    }
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public void send(String address, final String msg) {
        if (address == null) {
            throw new IllegalArgumentException();
        }
        Channel channel = this.channelPool.get(address).remove(0);
        ChannelFuture channelFuture = channel.writeAndFlush(msg);
        if(channelFuture != null){
            try {
                channelFuture.sync();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        channelPool.get(address).add(channel);
    }

    public static void main(String[] args) {
        NettyClientPool clientPool = new NettyClientPool();
        InetSocketAddress address = new InetSocketAddress("127.0.0.1", 8080);
        for (int i = 0; i < 100; i++) {
            clientPool.send("127.0.0.1", "你好");
        }
    }
}
