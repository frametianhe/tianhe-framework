package com.tianhe.framework.netty.online.client;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.HttpContent;
import io.netty.handler.codec.http.HttpResponse;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.LastHttpContent;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

import java.nio.charset.Charset;

/**
 * Created by weifeng.jiang on 2017-11-03 09:52.
 */
public class TestChannelHandler extends ChannelInboundHandlerAdapter{

    private ChannelInfo channelInfo;
    boolean respSuccess = false;
    HttpResponseStatus status = null;
    ByteBuf buf = Unpooled.buffer();
    String retStr = "";

    public TestChannelHandler(ChannelInfo channelInfo){
        this.channelInfo = channelInfo;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if(msg instanceof HttpResponse){
            HttpResponse response = (HttpResponse) msg;
            respSuccess = HttpResponseStatus.OK.equals(response.getStatus());
            status = response.getStatus();
        }
        if(msg instanceof HttpContent){
            HttpContent content = (HttpContent) msg;
            buf.writeBytes(content.content());
            if(content instanceof LastHttpContent){
                retStr = buf.toString(Charset.forName("UTF-8"));
                buf.clear();
                buf.release();
                ctx.close();
            }
        }
        if(respSuccess){
            System.out.println(retStr);
        }else{
            System.out.println("执行失败");
        }
     }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if(evt instanceof IdleStateEvent){
            IdleStateEvent event = (IdleStateEvent) evt;
            if(event.state() == IdleState.READER_IDLE){
                System.out.println("读超时");
                ctx.channel().close();
            }else if(event.state() == IdleState.WRITER_IDLE){
                System.out.println("写超时");
                ctx.channel().close();
            }else if(event.state() == IdleState.ALL_IDLE){
                System.out.println("所有超时");
                ctx.channel().close();
            }
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
