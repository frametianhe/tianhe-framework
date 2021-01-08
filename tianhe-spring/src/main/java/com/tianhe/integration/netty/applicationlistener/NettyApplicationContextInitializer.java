package com.tianhe.integration.netty.applicationlistener;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author: he.tian
 * @time: 2019-07-17 18:24
 */
public class NettyApplicationContextInitializer implements ApplicationContextInitializer {

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        applicationContext.addApplicationListener(new NettyApplicationListener());
    }
}
