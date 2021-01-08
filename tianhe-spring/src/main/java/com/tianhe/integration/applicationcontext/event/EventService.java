package com.tianhe.integration.applicationcontext.event;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

/**
 * Created by tianhe on 2019/12/11.
 */
@Service
public class EventService implements ApplicationContextAware{

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public void execute(){
        applicationContext.publishEvent(new TestEvent(this,"test"));
    }
}
