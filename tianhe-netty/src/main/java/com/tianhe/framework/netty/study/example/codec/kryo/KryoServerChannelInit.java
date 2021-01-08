package com.tianhe.framework.netty.study.example.codec.kryo;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * @author: he.tian
 * @time: 2018-08-02 12:24
 */
public class KryoServerChannelInit extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast(new LoggingHandler(LogLevel.INFO));
        KryoCodecServiceImpl kryoCodecService = new KryoCodecServiceImpl(KryoPoolFactory.getKryoPoolInstance());
        pipeline.addLast(new KryoEncoder(kryoCodecService));
        pipeline.addLast(new KryoDecoder(kryoCodecService));
        pipeline.addLast(new KryoServerHandler());
    }
}
