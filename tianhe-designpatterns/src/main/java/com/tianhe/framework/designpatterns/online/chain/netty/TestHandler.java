package com.tianhe.framework.designpatterns.online.chain.netty;

/**
 * Created by tianhe on 2019/11/8.
 */
public class TestHandler extends ChannelHandlerAdapter{

    @Override
    public void execute(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("testHandler execute "+msg);
        ctx.fireExecute(msg);
    }
}
