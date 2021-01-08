package com.tianhe.framework.zookeeper.zkclient;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.I0Itec.zkclient.ZkClient;

/**
 * Created by tianhe on 2019/9/29.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ZookeeperFactory {

    private static final ZkClient INSTANCE = new ZkClient("localhost:2181",6000,2000);

    public static ZkClient getInstance(){
        return INSTANCE;
    }
}
