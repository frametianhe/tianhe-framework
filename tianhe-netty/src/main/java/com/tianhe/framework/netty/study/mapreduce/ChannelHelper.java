package com.tianhe.framework.netty.study.mapreduce;

import io.netty.channel.Channel;
import io.netty.channel.ChannelId;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author: he.tian
 * @time: 2019-01-16 17:54
 */
public abstract class ChannelHelper {

    public static ConcurrentMap<ChannelId,Channel> CHANNEL_CONCURRENT_MAP = new ConcurrentHashMap<>();

    public static ScheduledExecutorService SCHEDULED_EXECUTOR = Executors.newSingleThreadScheduledExecutor();

    public static AtomicLong COUNT = new AtomicLong();

}
