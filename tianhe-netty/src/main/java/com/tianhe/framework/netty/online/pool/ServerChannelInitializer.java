package com.tianhe.framework.netty.online.pool;

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

import java.util.concurrent.TimeUnit;

/**
 * Created by tianhe on 2019/10/11.
 */
@AllArgsConstructor
public class ServerChannelInitializer extends ChannelInitializer<SocketChannel> {

    private NettyServerHandler serverHandler;

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        KryoMessageCoder kryoMessageCoder = new KryoMessageCoder(KryoPoolFactory.getKryoPoolInstance());
        pipeline.addLast(new LoggingHandler(LogLevel.INFO));
        pipeline.addLast(new KryoEncoder(kryoMessageCoder));
        pipeline.addLast(new KryoDecoder(kryoMessageCoder));
        pipeline.addLast(new IdleStateHandler(3000, 3000, 3000, TimeUnit.SECONDS));
        pipeline.addLast(serverHandler);
    }
}
