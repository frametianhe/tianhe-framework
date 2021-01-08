package com.tianhe.framework.netty.online.pool;

import io.netty.channel.Channel;
import io.netty.channel.pool.ChannelPoolHandler;

/**
 * Created by tianhe on 2019/10/16.
 */
public abstract class AbstractChannelPoolHandler implements ChannelPoolHandler {

    @Override
    public void channelAcquired(Channel ch) throws Exception {

    }

    @Override
    public void channelReleased(Channel ch) throws Exception {

    }
}
