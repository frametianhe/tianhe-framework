package com.tianhe.integration.netty.applicationcontextaware;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author: he.tian
 * @time: 2018-08-13 16:40
 */
@Component
public class ApplicationContextAwareInit implements ApplicationContextAware {

    @Autowired
    private AwareNettyServer nettyServer;

    //spring上下文初始化完成后会执行这个方法
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        nettyServer.start();
    }
}
