package com.tianhe.integration.applicationcontext.event;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * Created by tianhe on 2019/12/11.
 */
@Component
public class EventListener implements ApplicationListener<TestEvent>{

    @Override
    public void onApplicationEvent(TestEvent testEvent) {
        System.out.println(testEvent);
    }
}
