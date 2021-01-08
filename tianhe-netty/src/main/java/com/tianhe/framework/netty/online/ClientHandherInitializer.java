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
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.concurrent.TimeUnit;


/**
 * @author: he.tian
 * @time: 2018-11-01 17:25
 */
@AllArgsConstructor
@NoArgsConstructor
public class ClientHandherInitializer extends ChannelInitializer<SocketChannel>{

    private NettyClientHandler nettyClientHandler;

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        KryoMessageCoder kryoMessageCoder = new KryoMessageCoder(KryoPoolFactory.getKryoPoolInstance());
        pipeline.addLast(new LoggingHandler(LogLevel.INFO));
        pipeline.addLast(new KryoEncoder(kryoMessageCoder));
        pipeline.addLast(new KryoDecoder(kryoMessageCoder));
        pipeline.addLast("timeout",new IdleStateHandler(3000,3000,3000, TimeUnit.SECONDS));
        pipeline.addLast(nettyClientHandler);
    }
}
