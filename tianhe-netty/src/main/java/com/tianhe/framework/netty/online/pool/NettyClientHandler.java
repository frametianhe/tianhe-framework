package com.tianhe.framework.netty.online.pool;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by tianhe on 2019/10/11.
 */
@ChannelHandler.Sharable
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
public class NettyClientHandler extends ChannelInboundHandlerAdapter {

    private NettyClientChannelManager channelManager;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (IdleStateEvent.class.isAssignableFrom(evt.getClass())) {
            IdleStateEvent event = (IdleStateEvent) evt;
            if (event.state() == IdleState.READER_IDLE) {
                try {
                    channelManager.invalidateObject(IpUtil.toStringAddress(ctx.channel().remoteAddress()), ctx.channel());
                } catch (Exception e) {
                    log.error("连接池channel验证失败，异常信息={}",e);
                } finally {
                    channelManager.releaseChannel(ctx.channel(), IpUtil.getAddressFromContext(ctx));
                }
            }
            if (event == IdleStateEvent.WRITER_IDLE_STATE_EVENT) {

            }
        }
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
        channelManager.releaseChannel(ctx.channel(), null);
        ctx.close();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        channelManager.invalidateObject(null, ctx.channel());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        log.error("client handler出现异常，异常信息={}", cause);
        channelManager.releaseChannel(ctx.channel(), null);
        ctx.close();
    }
}
