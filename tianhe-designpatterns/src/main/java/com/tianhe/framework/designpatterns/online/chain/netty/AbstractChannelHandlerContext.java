package com.tianhe.framework.designpatterns.online.chain.netty;

/**
 * Created by tianhe on 2019/11/8.
 */
public abstract class AbstractChannelHandlerContext implements ChannelHandlerContext {

    volatile AbstractChannelHandlerContext next;

    volatile AbstractChannelHandlerContext prev;

    private final DefaultChannelPipeline pipeline;

    AbstractChannelHandlerContext(DefaultChannelPipeline pipeline) {
        this.pipeline = pipeline;
    }

    @Override
    public ChannelPipeline pipeline() {
        return pipeline;
    }

    @Override
    public ChannelHandlerContext fireExecute(Object msg) {
        invokeExecute(findContext(), msg);
        return this;
    }

    private AbstractChannelHandlerContext findContext() {
        AbstractChannelHandlerContext ctx = this;
        ctx = ctx.next;
        return ctx;
    }

    static void invokeExecute(AbstractChannelHandlerContext next, Object msg) {
        try {
            next.invokeExecute(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void invokeExecute(Object msg) {
        try {
            handler().execute(this, msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
