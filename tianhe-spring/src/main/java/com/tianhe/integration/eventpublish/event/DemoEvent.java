package com.tianhe.integration.eventpublish.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/**
 * Created by tianhe on 2019/12/11.
 */
public class DemoEvent extends ApplicationEvent{

    @Getter
    private String msg;

    public DemoEvent(Object source,String msg) {
        super(source);
        this.msg = msg;
    }
}
