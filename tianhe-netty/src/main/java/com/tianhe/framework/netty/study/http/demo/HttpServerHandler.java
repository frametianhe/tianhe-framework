package com.tianhe.framework.netty.study.http.demo;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

/**
 * Created by tianhe on 2019/12/13.
 */
public class HttpServerHandler extends ChannelInboundHandlerAdapter{

    HttpRequest request;

    /**
     * fullHttpRequest = httpRequest httpContent... lastHttpContent
     * fullHttpResponse = httpResponse httpContent... lastHttpContent
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if(msg instanceof HttpRequest){
            request = (HttpRequest) msg;
            String uri = request.getUri();
            System.out.println(uri);
        }
        if(msg instanceof HttpContent){
            HttpContent content = (HttpContent) msg;
            ByteBuf buf = content.content();
            System.out.println(buf.toString(CharsetUtil.UTF_8));
            buf.release();
            String res = "world";
            FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,HttpResponseStatus.OK,
                    Unpooled.wrappedBuffer(res.getBytes("utf-8")));
            response.headers().set(HttpHeaderNames.CONTENT_TYPE,HttpHeaderValues.TEXT_PLAIN);
            response.headers().set(HttpHeaderNames.CONTENT_LENGTH,response.content().readableBytes());
            if(HttpHeaders.isKeepAlive(request)){
                response.headers().set(HttpHeaderNames.CONNECTION,HttpHeaderValues.KEEP_ALIVE);
            }
            ctx.writeAndFlush(response);
        }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
