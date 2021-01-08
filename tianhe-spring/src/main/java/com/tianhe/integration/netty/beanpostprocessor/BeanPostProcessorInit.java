package com.tianhe.integration.netty.beanpostprocessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * @author: he.tian
 * @time: 2018-08-13 16:52
 */
@Component
public class BeanPostProcessorInit implements BeanPostProcessor {

    @Autowired
    private InitializerNettyServer nettyServer;

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
//        这里类似于执行init方法之前
        nettyServer.start();
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
//        nettyServer.start();也可以在这里，这里是bean初始化之后，类似于执行完init方法之后
        return bean;
    }
}
