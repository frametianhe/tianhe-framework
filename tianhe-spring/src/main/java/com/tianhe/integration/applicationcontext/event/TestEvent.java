package com.tianhe.integration.applicationcontext.event;

import lombok.Data;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/**
 * Created by tianhe on 2019/12/11.
 */
@Data
public class TestEvent extends ApplicationEvent{

    @Getter
    private String msg;

    public TestEvent(Object source, String msg) {
        super(source);
        this.msg = msg;
    }
}
