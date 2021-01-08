package com.tianhe.integration.netty.smartinitializingsingleton;

import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author: he.tian
 * @time: 2018-08-13 16:40
 */
@Component
public class SmartInitializingSingletonInit implements SmartInitializingSingleton {

    @Autowired
    private ListenerNettyServer nettyServer;

    @Override
    public void afterSingletonsInstantiated() {
        nettyServer.start();
    }
}
