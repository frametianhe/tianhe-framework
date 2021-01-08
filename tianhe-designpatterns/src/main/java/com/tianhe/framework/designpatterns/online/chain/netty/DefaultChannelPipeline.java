package com.tianhe.framework.designpatterns.online.chain.netty;

/**
 * Created by tianhe on 2019/11/8.
 */
public class DefaultChannelPipeline implements ChannelPipeline {

    private AbstractChannelHandlerContext first;

    private AbstractChannelHandlerContext end;

    public DefaultChannelPipeline(){
        end = new TailContext(this);
        first = new HeadContext(this);
        first.next = end;
        end.prev = first;
    }

    @Override
    public ChannelPipeline addFirst(ChannelHandler handler) {
        final AbstractChannelHandlerContext newCtx;
        synchronized (this) {
//            创建handlerContext
            newCtx = newContext(handler);

//            添加handler到链表first
            addFirst0(newCtx);
        }
        return this;
    }

    private void addFirst0(AbstractChannelHandlerContext newCtx) {
        AbstractChannelHandlerContext nextCtx = first.next;
        newCtx.prev = first;
        newCtx.next = nextCtx;
        first.next = newCtx;
        nextCtx.prev = newCtx;
    }

    @Override
    public ChannelPipeline addLast(ChannelHandler handler) {
        final AbstractChannelHandlerContext newCtx;
        synchronized (this){
//            创建handlerContext
            newCtx = newContext(handler);
//            添加handler到链表的last
            addLast0(newCtx);
        }
        return this;
    }

    private void addLast0(AbstractChannelHandlerContext newCtx) {
        AbstractChannelHandlerContext prev = end.prev;
        newCtx.prev = prev;
        newCtx.next = end;
        prev.next = newCtx;
        end.prev = newCtx;
    }

    @Override
    public ChannelHandler first() {
        ChannelHandlerContext first = firstContext();
        if(first == null){
            return null;
        }
        return first.handler();
    }

    @Override
    public ChannelHandlerContext firstContext() {
        AbstractChannelHandlerContext channelHandlerContext = first.next;
        if(channelHandlerContext == end){
            return null;
        }
        return channelHandlerContext.next;
    }

    @Override
    public ChannelHandler last() {
        AbstractChannelHandlerContext channelHandlerContext = end.prev;
        if(channelHandlerContext == first){
            return null;
        }
        return channelHandlerContext.handler();
    }

    @Override
    public ChannelHandlerContext lastContext() {
        AbstractChannelHandlerContext channelHandlerContext = end.prev;
        if(channelHandlerContext == first){
            return null;
        }
        return channelHandlerContext;
    }

    @Override
    public ChannelHandlerContext context(ChannelHandler handler) {
        if(handler == null){
            throw new NullPointerException("handler");
        }
        AbstractChannelHandlerContext ctx = first.next;
        for (;;){
            if(ctx == null){
                return null;
            }
            if(ctx.handler() == handler){
                return ctx;
            }
            ctx = ctx.next;
        }
    }

    @Override
    public ChannelPipeline fireExecute(Object msg) {
        AbstractChannelHandlerContext.invokeExecute(first,msg);
        return this;
    }

    private AbstractChannelHandlerContext newContext(ChannelHandler handler) {
        return new DefaultChannelHandlerContext(this,handler);
    }

    class TailContext extends AbstractChannelHandlerContext implements ChannelHandler{

        TailContext(DefaultChannelPipeline pipeline){
            super(pipeline);
        }

        @Override
        public void execute(ChannelHandlerContext ctx, Object msg) throws Exception {

        }

        @Override
        public ChannelHandler handler() {
            return this;
        }
    }

    class HeadContext extends AbstractChannelHandlerContext implements ChannelHandler{

        HeadContext(DefaultChannelPipeline pipeline){
            super(pipeline);
        }

        @Override
        public void execute(ChannelHandlerContext ctx, Object msg) throws Exception {
            ctx.fireExecute(msg);
        }

        @Override
        public ChannelHandler handler() {
            return this;
        }
    }
}
