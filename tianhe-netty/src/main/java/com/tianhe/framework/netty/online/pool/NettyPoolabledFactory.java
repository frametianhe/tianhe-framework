package com.tianhe.framework.netty.online.pool;

import io.netty.channel.Channel;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.pool.KeyedPoolableObjectFactory;

import java.net.InetSocketAddress;

/**
 * Created by tianhe on 2019/10/16.
 */
@Slf4j
public class NettyPoolabledFactory implements KeyedPoolableObjectFactory<NettyPoolKey, Channel> {

    private NettyClient nettyClient;

    public NettyPoolabledFactory(NettyClient nettyClient){
        this.nettyClient = nettyClient;
    }

    @Override
    public Channel makeObject(NettyPoolKey nettyPoolKey) throws Exception {
        InetSocketAddress address = IpUtil.toInetSocketAddress(nettyPoolKey.getAddress());
        log.info("netty连接池创建channel     key=" + nettyPoolKey);
        Channel newChannel = nettyClient.getNewChannel(address);
        return newChannel;
    }

    @Override
    public void destroyObject(NettyPoolKey nettyPoolKey, Channel channel) throws Exception {
        if (null != channel) {
            log.info("销毁channel " + channel);
            channel.disconnect();
            channel.close();
        }
    }

    @Override
    public boolean validateObject(NettyPoolKey nettyPoolKey, Channel channel) {
        if (null != channel && channel.isActive()) {
            return true;
        }
        log.error("channel验证失败  " + channel);
        return false;
    }

    @Override
    public void activateObject(NettyPoolKey nettyPoolKey, Channel channel) throws Exception {

    }

    @Override
    public void passivateObject(NettyPoolKey nettyPoolKey, Channel channel) throws Exception {

    }
}
