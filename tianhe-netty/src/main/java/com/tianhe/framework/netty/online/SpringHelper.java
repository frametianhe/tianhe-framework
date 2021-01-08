package com.tianhe.framework.netty.online;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @author: he.tian
 * @time: 2018-11-01 17:53
 */
public class SpringHelper implements ApplicationContextAware{

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public static  <T> T getBean(Class<T> type){
        return applicationContext.getBean(type);
    }
}
