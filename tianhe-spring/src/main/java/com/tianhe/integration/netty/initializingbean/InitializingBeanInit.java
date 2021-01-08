package com.tianhe.integration.netty.initializingbean;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author: he.tian
 * @time: 2018-08-13 16:52
 */
@Component
public class InitializingBeanInit implements InitializingBean {

    @Autowired
    private InitializerNettyServer nettyServer;

    @Override
    public void afterPropertiesSet() throws Exception {
        nettyServer.start();
    }
}
