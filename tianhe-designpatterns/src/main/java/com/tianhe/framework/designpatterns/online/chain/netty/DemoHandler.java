package com.tianhe.framework.designpatterns.online.chain.netty;

/**
 * Created by tianhe on 2019/11/8.
 */
public class DemoHandler extends ChannelHandlerAdapter{

    @Override
    public void execute(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("demoHandler execute "+msg);
        ctx.fireExecute(msg);
    }
}
