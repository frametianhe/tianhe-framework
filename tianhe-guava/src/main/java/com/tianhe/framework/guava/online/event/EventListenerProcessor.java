package com.tianhe.framework.guava.online.event;

import org.springframework.aop.support.AopUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.annotation.AnnotationUtils;

/**
 * Created by tianhe on 2019/10/8.
 */
public class EventListenerProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Class<?> targetClass = AopUtils.getTargetClass(bean);
        Listener listener = AnnotationUtils.findAnnotation(targetClass, Listener.class);
        if (listener != null) {
            EventBusFactory.getInstance().register(bean);
        }
        return bean;
    }
}
