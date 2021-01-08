package com.tianhe.framework.netty.online;

import io.netty.channel.Channel;
import io.netty.channel.ChannelId;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author: he.tian
 * @time: 2018-11-23 16:42
 */
@NoArgsConstructor
public class ChannelManager {

    private static ChannelManager INSTANCE = new ChannelManager();

    @Getter
    @Setter
    private int maxConnection = 100;

    @Getter
    @Setter
    private int currentConnection;

    @Getter
    @Setter
    private boolean allowConnection = true;

    @Getter
    @Setter
    private ConcurrentMap<ChannelId,Channel> channelConcurrentMap = new ConcurrentHashMap<>();

    public void addChannel(Channel channel){
        channelConcurrentMap.put(channel.id(),channel);
        currentConnection = channelConcurrentMap.values().size();
        allowConnection = maxConnection != currentConnection;
    }

    public void removeChannel(Channel channel){
        channelConcurrentMap.remove(channel.id());
        currentConnection  = channelConcurrentMap.values().size();
        allowConnection = maxConnection != currentConnection;
    }

    public static ChannelManager getInstance(){
        return INSTANCE;
    }

    /**
     * 根据ip地址获取channel
     * @param channelId
     * @return
     */
    public Channel getChannelByChannelId(ChannelId channelId){
       return channelConcurrentMap.get(channelId);
    }
}
