package com.tianhe.framework.guava.study.event;

import com.google.common.eventbus.EventBus;

/**
 * @author: he.tian
 * @time: 2019-08-05 14:57
 */
public class TestEventBus {

    public static void main(String[] args) {
        EventBus eventBus = new EventBus();
        EventListener eventListener = new EventListener();
        eventBus.register(eventListener);
        eventBus.post(new TestEvent("a"));
        eventBus.post(new TestEvent("b"));
        eventBus.post(new TestEvent("c"));
        System.out.println(eventListener.getMessage());
    }
}
