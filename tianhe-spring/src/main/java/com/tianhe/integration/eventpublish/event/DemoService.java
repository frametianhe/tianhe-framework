package com.tianhe.integration.eventpublish.event;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

/**
 * Created by tianhe on 2019/12/11.
 */
@Service
public class DemoService implements ApplicationEventPublisherAware{

    @EventListener
    public void eventExecute(DemoEvent demoEvent){
        System.out.println(demoEvent);
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        applicationEventPublisher.publishEvent(new DemoEvent(this,"demo"));
    }
}
