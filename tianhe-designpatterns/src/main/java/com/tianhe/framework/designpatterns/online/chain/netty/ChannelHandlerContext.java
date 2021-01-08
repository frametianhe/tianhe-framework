package com.tianhe.framework.designpatterns.online.chain.netty;

/**
 * Created by tianhe on 2019/11/8.
 */
public interface ChannelHandlerContext {

    ChannelHandler handler();

    ChannelHandlerContext fireExecute(Object msg);

    ChannelPipeline pipeline();
}
