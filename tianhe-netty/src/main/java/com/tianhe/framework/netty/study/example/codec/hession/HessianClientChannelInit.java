package com.tianhe.framework.netty.study.example.codec.hession;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * @author: he.tian
 * @time: 2018-08-02 11:32
 */
public class HessianClientChannelInit extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast(new LoggingHandler(LogLevel.INFO));
        HessianCodecServiceImpl hessianCodecService = new HessianCodecServiceImpl();
        pipeline.addLast(new HessianEncoder(hessianCodecService));
        pipeline.addLast(new HessianDecoder(hessianCodecService));
        pipeline.addLast(new HessianClientHandler());
    }
}
