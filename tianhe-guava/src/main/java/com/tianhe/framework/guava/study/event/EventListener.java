package com.tianhe.framework.guava.study.event;

import com.google.common.eventbus.Subscribe;

/**
 * @author: he.tian
 * @time: 2019-08-05 14:55
 */
public class EventListener {

    private String message;

    @Subscribe
    public void listen(TestEvent event){
        this.message = event.getMessage();
        System.out.println("接收消息    "+message);
    }

    public String getMessage() {
        return message;
    }
}
