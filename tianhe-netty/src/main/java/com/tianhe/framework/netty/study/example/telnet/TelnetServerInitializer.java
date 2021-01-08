package com.tianhe.framework.netty.study.example.telnet;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.ssl.SslContext;

/**
 * Created by weifeng.jiang on 2018-07-22 19:10.
 */
public class TelnetServerInitializer extends ChannelInitializer<SocketChannel>{

    private static final StringDecoder DECODER = new StringDecoder();
    private static final StringEncoder ENCODER = new StringEncoder();
    private static final TelnetServerHandler SERVER_HANDLER = new TelnetServerHandler();
    private final SslContext sslContext;

    public TelnetServerInitializer(SslContext sslContext){
        this.sslContext = sslContext;
    }

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline p = ch.pipeline();
        if(sslContext != null){
            p.addLast(sslContext.newHandler(ch.alloc()));
        }

        p.addLast(new DelimiterBasedFrameDecoder(8192, Delimiters.lineDelimiter()));
        p.addLast(DECODER);
        p.addLast(ENCODER);
        p.addLast(SERVER_HANDLER);
    }
}
