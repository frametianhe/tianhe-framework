package com.tianhe.framework.netty.study.mapreduce;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelId;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.Set;

/**
 * @author: he.tian
 * @time: 2019-01-16 17:50
 */
public class ServerHandler extends ChannelInboundHandlerAdapter{

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("服务端接收="+msg);
        ChannelHelper.COUNT.incrementAndGet();

        if(ChannelHelper.COUNT.get() == 2){
            Set<ChannelId> channelIds = ChannelHelper.CHANNEL_CONCURRENT_MAP.keySet();
            System.out.println("客户端人数="+channelIds.size());
            for (ChannelId channelId : channelIds) {
                ChannelHelper.CHANNEL_CONCURRENT_MAP.get(channelId).writeAndFlush("你们回去吧");
            }
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        ctx.close();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        ChannelHelper.CHANNEL_CONCURRENT_MAP.put(ctx.channel().id(),ctx.channel());
    }
}
