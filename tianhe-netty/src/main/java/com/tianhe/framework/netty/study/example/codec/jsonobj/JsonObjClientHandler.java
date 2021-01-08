package com.tianhe.framework.netty.study.example.codec.jsonobj;

import com.alibaba.fastjson.JSONObject;
import com.tianhe.framework.netty.study.example.User;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author: he.tian
 * @time: 2018-08-02 12:27
 */
public class JsonObjClientHandler extends ChannelInboundHandlerAdapter{

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        User user = new User();
        user.setUserName("userName");
        String userStr = JSONObject.toJSONString(user);
        ctx.writeAndFlush(Unpooled.copiedBuffer(userStr.getBytes()));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
