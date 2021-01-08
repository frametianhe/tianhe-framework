package com.tianhe.framework.designpatterns.online.chain.netty;

/**
 * Created by tianhe on 2019/11/8.
 */
public class DefaultChannelHandlerContext extends AbstractChannelHandlerContext{

    private final ChannelHandler handler;

    DefaultChannelHandlerContext(DefaultChannelPipeline pipeline,ChannelHandler handler){
        super(pipeline);
        if(handler == null){
            throw new NullPointerException("handler");
        }
        this.handler = handler;
    }

    @Override
    public ChannelHandler handler() {
        return handler;
    }
}
