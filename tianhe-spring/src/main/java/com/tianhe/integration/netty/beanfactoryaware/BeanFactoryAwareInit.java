package com.tianhe.integration.netty.beanfactoryaware;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author: he.tian
 * @time: 2018-08-13 16:52
 */
@Component
public class BeanFactoryAwareInit implements BeanFactoryAware {

    @Autowired
    private InitializerNettyServer nettyServer;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        nettyServer.start();
    }
}
