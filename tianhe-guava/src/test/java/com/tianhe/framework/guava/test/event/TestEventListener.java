package com.tianhe.framework.guava.test.event;

import com.google.common.eventbus.Subscribe;
import com.tianhe.framework.guava.online.event.EventListener;
import com.tianhe.framework.guava.online.event.Listener;

/**
 * Created by tianhe on 2019/10/8.
 */
@Listener
public class TestEventListener implements EventListener<TestEvent> {

    @Subscribe
    @Override
    public void onEvent(TestEvent event) {
        System.out.println("收到事件监听后的业务逻辑处理");
    }
}
