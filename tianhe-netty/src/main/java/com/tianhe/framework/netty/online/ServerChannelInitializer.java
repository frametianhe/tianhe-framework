package com.tianhe.framework.netty.online;

import com.tianhe.framework.netty.online.codec.kryo.KryoDecoder;
import com.tianhe.framework.netty.online.codec.kryo.KryoEncoder;
import com.tianhe.framework.netty.online.codec.kryo.KryoMessageCoder;
import com.tianhe.framework.netty.online.codec.kryo.KryoPoolFactory;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleStateHandler;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.TimeUnit;

/**
 * @author: he.tian
 * @time: 2018-10-16 11:11
 */
public class ServerChannelInitializer extends ChannelInitializer<SocketChannel>{

    @Autowired
    private NettyServerHandler nettyServerHandler;

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        KryoMessageCoder kryoMessageCoder = new KryoMessageCoder(KryoPoolFactory.getKryoPoolInstance());
        pipeline.addLast(new LoggingHandler(LogLevel.INFO));
        pipeline.addLast(new KryoEncoder(kryoMessageCoder));
        pipeline.addLast(new KryoDecoder(kryoMessageCoder));
        pipeline.addLast("timeout",new IdleStateHandler(3000,3000,3000, TimeUnit.SECONDS));
        pipeline.addLast(nettyServerHandler);
    }
}
