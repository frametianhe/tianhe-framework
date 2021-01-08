package com.tianhe.framework.designpatterns.online.chain.netty;

/**
 * Created by tianhe on 2019/11/8.
 */
public interface ChannelHandler {

    void execute(ChannelHandlerContext ctx,Object msg) throws Exception;
}
