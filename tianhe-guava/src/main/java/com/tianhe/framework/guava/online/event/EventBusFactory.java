package com.tianhe.framework.guava.online.event;

import com.google.common.eventbus.EventBus;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Created by tianhe on 2019/10/8.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EventBusFactory {

    private static final EventBus INSTANCE = new EventBus();

    public static EventBus getInstance() {
        return INSTANCE;
    }
}
