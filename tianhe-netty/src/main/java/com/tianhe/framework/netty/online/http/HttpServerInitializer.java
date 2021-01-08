package com.tianhe.framework.netty.online.http;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by tianhe on 2019/12/13.
 */
public class HttpServerInitializer extends ChannelInitializer<SocketChannel>{

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
//        pipeline.addLast(new HttpRequestDecoder());
//        pipeline.addLast(new HttpObjectAggregator(1024 * 1024));
//        pipeline.addLast(new HttpResponseEncoder());coder());
        pipeline.addLast(new HttpServerCodec());
        pipeline.addLast(new HttpObjectAggregator(1024 * 1024));
        Map<String,Handler> handlerMappings = new HashMap<>();
        handlerMappings.put("demoAction",new DemoHandler());
        pipeline.addLast(new HttpServerHandler(handlerMappings));
    }
}
