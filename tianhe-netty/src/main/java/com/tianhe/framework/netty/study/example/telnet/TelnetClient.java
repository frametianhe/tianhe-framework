package com.tianhe.framework.netty.study.example.telnet;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by weifeng.jiang on 2018-07-22 18:56.
 */
public class TelnetClient {

    static final boolean SSL = System.getProperty("ssl") != null;
    static final String HOST = System.getProperty("host","127.0.0.1");
    static final int PORT = Integer.parseInt(System.getProperty("port",SSL ? "8992" : "8023"));

    public static void main(String[] args) throws Exception{
        final SslContext sslContext;
        if(SSL){
            sslContext = SslContextBuilder.forClient().trustManager(InsecureTrustManagerFactory.INSTANCE).build();
        }else{
            sslContext = null;
        }

        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new TelnetClientInitializer(sslContext));
            Channel ch = b.connect(HOST,PORT).sync().channel();
            ChannelFuture lastWriteFuture = null;
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            for (;;){
                String line = in.readLine();
                if(line == null){
                    break;
                }
                lastWriteFuture = ch.writeAndFlush(line+"\r\n");
                if("bye".equals(line.toLowerCase())){
                    ch.closeFuture().sync();
                    break;
                }
            }
            if(lastWriteFuture!= null){
                lastWriteFuture.sync();
            }
        }finally {
            group.shutdownGracefully();
        }
    }
}
