package com.tianhe.framework.designpatterns.online.singleton;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserHungryFactory {

    private static final User INSTANCE = new User();

    public User getInstance() {
        return INSTANCE;
    }
}
