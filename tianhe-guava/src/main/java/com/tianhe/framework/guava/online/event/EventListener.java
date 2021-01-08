package com.tianhe.framework.guava.online.event;

/**
 * Created by tianhe on 2019/10/8.
 */
public interface EventListener<T> {

    void onEvent(T event);
}
