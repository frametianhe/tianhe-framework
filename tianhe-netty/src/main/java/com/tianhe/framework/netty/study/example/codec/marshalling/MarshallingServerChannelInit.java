package com.tianhe.framework.netty.study.example.codec.marshalling;

import com.tianhe.framework.netty.study.example.User;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * @author: he.tian
 * @time: 2018-08-02 12:24
 */
public class MarshallingServerChannelInit extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast(new LoggingHandler(LogLevel.INFO));
        pipeline.addLast(new ObjectDecoder(ClassResolvers.cacheDisabled(User.class.getClassLoader())));
        pipeline.addLast(new MarshallingServerHandler());
    }
}
