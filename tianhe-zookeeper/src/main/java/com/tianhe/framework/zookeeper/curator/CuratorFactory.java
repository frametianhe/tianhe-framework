package com.tianhe.framework.zookeeper.curator;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * Created by tianhe on 2019/9/29.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CuratorFactory {

    private static CuratorFramework INSTANCE = CuratorFrameworkFactory.newClient("localhost:2181",new ExponentialBackoffRetry(1000,3));

    public static CuratorFramework getInstance(){
        return INSTANCE;
    }
}
