package com.tianhe.framework.netty.study.http;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

/**
 * @author: he.tian
 * @time: 2018-08-09 19:50
 */
public class HttpServerHandler extends ChannelInboundHandlerAdapter {

    private HttpRequest request;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if(msg instanceof HttpRequest){
            request = (HttpRequest) msg;
            String uri = request.getUri();
            System.out.println("uri======="+uri);
        }
        if(msg instanceof HttpContent){
            HttpContent content = (HttpContent) msg;
            ByteBuf buf = content.content();
            System.out.println("buf======"+buf.toString(CharsetUtil.UTF_8));
            buf.release();
            String res = "i am ok";
            FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, Unpooled.wrappedBuffer(res.getBytes(CharsetUtil.UTF_8)));
            response.headers().set(HttpHeaders.Names.CONTENT_TYPE,"text/plain");
            response.headers().set(HttpHeaders.Names.CONTENT_LENGTH,response.content().readableBytes());
            if(HttpHeaders.isKeepAlive(request)){
                response.headers().set(HttpHeaders.Names.CONNECTION, HttpHeaders.Values.KEEP_ALIVE);
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
        cause.printStackTrace();
        ctx.close();
    }
}
