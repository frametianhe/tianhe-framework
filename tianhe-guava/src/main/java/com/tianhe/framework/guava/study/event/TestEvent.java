package com.tianhe.framework.guava.study.event;

/**
 * @author: he.tian
 * @time: 2019-08-05 14:53
 */
public class TestEvent {

    private String message;

    public TestEvent(String message){
        this.message = message;
        System.out.println("接收事件    "+message);
    }

    public String getMessage() {
        return message;
    }
}
