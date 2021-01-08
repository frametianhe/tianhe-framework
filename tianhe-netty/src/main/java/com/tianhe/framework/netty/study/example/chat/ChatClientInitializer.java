package com.tianhe.framework.netty.study.example.chat;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.ssl.SslContext;

/**
 * Created by weifeng.jiang on 2018-07-22 18:22.
 */
public class ChatClientInitializer extends ChannelInitializer<SocketChannel> {

    private final SslContext sslContext;

    public ChatClientInitializer(SslContext sslContext){
        this.sslContext = sslContext;
    }

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline p = ch.pipeline();
        p.addLast(sslContext.newHandler(ch.alloc(),"127.0.0.1",9080));
        p.addLast(new DelimiterBasedFrameDecoder(8192, Delimiters.lineDelimiter()));
        p.addLast(new StringDecoder());
        p.addLast(new StringEncoder());
        p.addLast(new ChatClientHandler());
    }
}
