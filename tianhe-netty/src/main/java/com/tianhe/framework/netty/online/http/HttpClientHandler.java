package com.tianhe.framework.netty.online.http;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

/**
 * Created by tianhe on 2019/12/13.
 */
public class HttpClientHandler extends ChannelInboundHandlerAdapter{

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        FullHttpResponse response = (FullHttpResponse) msg;
        ByteBuf content = response.content();
        HttpHeaders headers = response.headers();
        System.out.println(content.toString(CharsetUtil.UTF_8));
        System.out.println(headers.toString());
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        String uri = "/demoAction";
//        uri = uri+"?a=a";//get方法在这里传参数
//        String msg = "hello";
//        FullHttpRequest request = new DefaultFullHttpRequest(HttpVersion.HTTP_1_1,HttpMethod.GET,uri, Unpooled.wrappedBuffer(msg.getBytes("utf-8")));

        String msg = "userName=userName";//post方式这样传递参数
        FullHttpRequest request = new DefaultFullHttpRequest(HttpVersion.HTTP_1_1,HttpMethod.POST,uri, Unpooled.wrappedBuffer(msg.getBytes("utf-8")));

        request.headers().set(HttpHeaderNames.CONTENT_TYPE,"text/plain;charset=utf-8")
                //开启长连接
                .set(HttpHeaderNames.CONNECTION,HttpHeaderValues.KEEP_ALIVE)
                .set(HttpHeaderNames.CONTENT_LENGTH,request.content().readableBytes());
        ctx.writeAndFlush(request);
    }
}
