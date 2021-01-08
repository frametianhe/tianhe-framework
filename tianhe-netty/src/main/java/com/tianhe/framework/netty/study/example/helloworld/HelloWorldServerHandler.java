package com.tianhe.framework.netty.study.example.helloworld;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.*;
import io.netty.util.AsciiString;

/**
 * Created by weifeng.jiang on 2018-07-22 14:18.
 */
public class HelloWorldServerHandler extends ChannelInboundHandlerAdapter {

    private static final byte[] CONTENT = "hello world".getBytes();
    private static final AsciiString CONTENT_TYPE = AsciiString.cached("Content-Type");
    private static final AsciiString CONTENT_LENGTH = AsciiString.cached("Content-Length");
    private static final AsciiString CONNECTION = AsciiString.cached("Connection");
    private static final AsciiString KEEP_ALIVE = AsciiString.cached("Keep-alive");

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if(msg instanceof HttpRequest){
            HttpRequest req = (HttpRequest) msg;
            boolean keepAlive = HttpUtil.isKeepAlive(req);
            FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, Unpooled.wrappedBuffer(CONTENT));
            response.headers().set(CONTENT_TYPE,"text/plain");
            response.headers().setInt(CONTENT_LENGTH,response.content().readableBytes());
            if(!keepAlive){
                ctx.write(response).addListener(ChannelFutureListener.CLOSE);
            }else{
                response.headers().set(CONNECTION,KEEP_ALIVE);
                ctx.write(response);
            }
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
