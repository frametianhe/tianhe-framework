package com.tianhe.framework.netty.study.http;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.HttpContent;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpResponse;
import io.netty.util.CharsetUtil;

/**
 * @author: he.tian
 * @time: 2018-08-09 20:03
 */
public class HttpClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if(msg instanceof HttpResponse){
            HttpResponse response = (HttpResponse) msg;
            System.out.println("content-type========="+response.headers().get(HttpHeaders.Names.CONTENT_TYPE));
        }
        if(msg instanceof HttpContent){
            HttpContent content = (HttpContent) msg;
            ByteBuf buf = content.content();
            System.out.println("buf======="+buf.toString(CharsetUtil.UTF_8));
            buf.release();
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
