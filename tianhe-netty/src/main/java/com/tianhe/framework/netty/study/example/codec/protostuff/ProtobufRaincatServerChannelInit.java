package com.tianhe.framework.netty.study.example.codec.protostuff;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * @author: he.tian
 * @time: 2018-08-02 12:24
 */
public class ProtobufRaincatServerChannelInit extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast(new LoggingHandler(LogLevel.INFO));
        ProtostuffCodecServiceImpl protostuffCodecService = new ProtostuffCodecServiceImpl();
        pipeline.addLast(new ProtostuffEncoder(protostuffCodecService));
        pipeline.addLast(new ProtostuffDecoder(protostuffCodecService));
        pipeline.addLast(new ProtobufRaincatServerHandler());
    }
}
