package com.tianhe.framework.netty.online.pool;

import com.tianhe.framework.zookeeper.curator.CuratorFactory;
import io.netty.channel.Channel;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.pool2.impl.GenericKeyedObjectPool;
import org.apache.curator.framework.CuratorFramework;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by tianhe on 2019/10/16.
 */
@Slf4j
public class NettyClientChannelManager {

    private final ConcurrentMap<String, Object> channelLocks = new ConcurrentHashMap<>(64);

    private final ConcurrentMap<String, NettyPoolKey> poolKeyMap = new ConcurrentHashMap<>(64);

    @Getter
    private final ConcurrentMap<String, Channel> channels = new ConcurrentHashMap<>(64);

    private GenericKeyedObjectPool<NettyPoolKey, Channel> nettyClientKeyPool;

    private CuratorFramework curatorFramework;

    public NettyClientChannelManager(GenericKeyedObjectPool nettyClientKeyPool) {
        this.nettyClientKeyPool = nettyClientKeyPool;
        this.curatorFramework = CuratorFactory.getInstance();
    }

    public Channel acquireChannel(String address) {
        Channel channel = channels.get(address);
        if (channel != null) {
            Channel backChannel = channel = getExistAliveChannel(channel, address);
            if (backChannel != null && backChannel.isActive()) {
                return backChannel;
            }
        }
        channelLocks.putIfAbsent(address, new Object());
        synchronized (channelLocks.get(address)) {
            return doConnect(address);
        }
    }

    private Channel getExistAliveChannel(Channel channel, String address) {
        if (channel.isActive()) {
            return channel;
        } else {
            Channel backChannel = channels.get(address);
            if (backChannel != null && backChannel.isActive()) {
                return backChannel;
            }
        }
        return null;
    }

    private Channel doConnect(String address) {
        Channel channel = channels.get(address);
        if (channel != null && channel.isActive()) {
            return channel;
        }
        Channel channelFromPool;
        NettyPoolKey currentPoolKey = new NettyPoolKey(address);
        poolKeyMap.putIfAbsent(address, currentPoolKey);
        try {
            channelFromPool = nettyClientKeyPool.borrowObject(poolKeyMap.get(address));
            channels.put(address, channelFromPool);
        } catch (Exception e) {
            log.error("从连接池获取channel失败,异常信息={}", e);
            throw new RuntimeException("从连接池获取channel失败");
        }
        return channelFromPool;
    }

    public void releaseChannel(Channel channel, String address) {
        if (channel == null || address == null) {
            return;
        }
        try {
            synchronized (channelLocks.get(address)) {
                Channel willReleaseChannel = channels.get(address);
                if (willReleaseChannel == null) {
                    nettyClientKeyPool.returnObject(poolKeyMap.get(address), channel);
                    return;
                }
                if (willReleaseChannel.compareTo(channel) == 0) {
                    destoryChannel(address, channel);
                } else {
                    nettyClientKeyPool.returnObject(poolKeyMap.get(address), channel);
                }
            }
        } catch (Exception e) {
            log.error("释放连接异常，异常信息={}", e);
        }
    }

    private void destoryChannel(String address, Channel channel) {
        if (channel == null) {
            return;
        }
        try {
            if (channel.equals(channels.get(address))) {
                channels.remove(address);
            }
            nettyClientKeyPool.returnObject(poolKeyMap.get(address), channel);
        } catch (Exception e) {
            log.error("连接池返回连接异常，异常信息={}", e);
        }
    }

    public void reconnect(String host) {
        acquireChannel(host);
    }

    public void invalidateObject(String address, Channel channel) {
        try {
            nettyClientKeyPool.invalidateObject(poolKeyMap.get(address), channel);
        } catch (Exception e) {
            log.error("验证连接失败，异常信息={}", e);
        }
    }
}
