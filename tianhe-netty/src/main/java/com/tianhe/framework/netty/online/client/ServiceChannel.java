package com.tianhe.framework.netty.online.client;

import io.netty.channel.ChannelHandler;

/**
 * Created by weifeng.jiang on 2017-11-02 21:36.
 */
public interface ServiceChannel {

    public void service(String param, ChannelHandler channelHandler) throws Exception;
}
