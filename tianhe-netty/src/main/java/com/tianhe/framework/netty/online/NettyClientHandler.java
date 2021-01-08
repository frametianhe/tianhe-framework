package com.tianhe.framework.netty.online;

import com.tianhe.framework.netty.online.codec.kryo.Request;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author: he.tian
 * @time: 2018-11-01 17:44
 */
@ChannelHandler.Sharable
public class NettyClientHandler extends ChannelInboundHandlerAdapter {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Getter
    private volatile ChannelHandlerContext ctx;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (IdleStateEvent.class.isAssignableFrom(evt.getClass())) {
            IdleStateEvent event = (IdleStateEvent) evt;
            if (event.state() == IdleState.READER_IDLE) {
                SpringHelper.getBean(NettyClient.class).doConnect();
            } else if (event.state() == IdleState.WRITER_IDLE) {
                Request request = new Request();
                request.setAction(Request.ActionEnum.HEART_BEAT.getCode());
                ctx.writeAndFlush(request);
                logger.info("netty client 发送 HEART_BEAT 请求");
            } else if (event.state() == IdleState.ALL_IDLE) {
                SpringHelper.getBean(NettyClient.class).doConnect();
            }
        }
    }

    public void send(Request request) {
        logger.info("netty client 发送请求", "请求类型=", request.getAction());
        if (ctx != null && ctx.channel() != null && ctx.channel().isActive()) {
            ctx.writeAndFlush(request);
        }
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
        SpringHelper.getBean(NettyClient.class).doConnect();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        this.ctx = ctx;
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        logger.error("nettyClientHandler 出现异常", cause);
        if (ctx.channel().isActive()) {
            ctx.close();
        }
    }
}