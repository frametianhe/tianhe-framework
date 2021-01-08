package com.tianhe.framework.designpatterns.online.chain.netty;

/**
 * Created by tianhe on 2019/11/8.
 */
public interface ChannelPipeline {

    ChannelPipeline addFirst(ChannelHandler handler);

    ChannelPipeline addLast(ChannelHandler handler);

    ChannelHandler first();

    ChannelHandlerContext firstContext();

    ChannelHandler last();

    ChannelHandlerContext lastContext();

    ChannelHandlerContext context(ChannelHandler handler);

    ChannelPipeline fireExecute(Object msg);
}
