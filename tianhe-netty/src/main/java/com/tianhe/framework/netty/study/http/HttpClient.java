package com.tianhe.framework.netty.study.http;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.*;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.util.CharsetUtil;

import java.net.URI;

/**
 * @author: he.tian
 * @time: 2018-08-09 20:06
 */
public class HttpClient {

    public static void main(String[] args) throws Exception {
        EventLoopGroup workGroup = new NioEventLoopGroup();
        Bootstrap b = new Bootstrap();
        b.group(workGroup)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.SO_KEEPALIVE,true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ChannelPipeline pipeline = ch.pipeline();
                        pipeline.addLast(new LoggingHandler(LogLevel.INFO));
                        pipeline.addLast(new HttpResponseDecoder());
                        pipeline.addLast(new HttpRequestEncoder());
                        pipeline.addLast(new HttpClientHandler());
                    }
                });
        ChannelFuture channelFuture = b.connect("127.0.0.1", 8090).sync();
        URI uri = new URI("http://127.0.0.1:8090");
        String msg = "are you ok?";
        DefaultFullHttpRequest request = new DefaultFullHttpRequest(HttpVersion.HTTP_1_1, HttpMethod.GET,uri.toASCIIString(), Unpooled.wrappedBuffer(msg.getBytes(CharsetUtil.UTF_8)));
        request.headers().set(HttpHeaders.Names.HOST, "127.0.0.1");
        request.headers().set(HttpHeaders.Names.CONNECTION, HttpHeaders.Values.KEEP_ALIVE);
        request.headers().set(HttpHeaders.Names.CONTENT_LENGTH,request.content().readableBytes());
        channelFuture.channel().writeAndFlush(request);
        channelFuture.channel().closeFuture().sync();

        workGroup.shutdownGracefully();
    }
}
