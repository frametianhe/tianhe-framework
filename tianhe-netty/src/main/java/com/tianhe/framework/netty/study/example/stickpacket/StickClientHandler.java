package com.tianhe.framework.netty.study.example.stickpacket;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.concurrent.TimeUnit;

/**
 * 通过客户端输出的netty日志可以看出已经出现粘包了，但是打印到控制台的信息和日志中打印的信息不一致，netty byteBuf可能用的是直接缓冲区，这时候读的信息可能还是上一次的缓存信息，
 * 可以看下有之前的java进程没有结束的没有，把这个进行杀掉重新来执行就可以了
 * @author: he.tian
 * @time: 2018-08-14 19:13
 */
public class StickClientHandler extends ChannelInboundHandlerAdapter{

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        for (int i=0;i<100;i++){
//            这里用来模拟网络异常，加大粘包出现的概率
            if(i%2==0){
                TimeUnit.SECONDS.sleep(2);
            }
            ctx.writeAndFlush(Unpooled.copiedBuffer("aaaaa".getBytes()));
        }
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("接收到服务端返回======="+msg);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
