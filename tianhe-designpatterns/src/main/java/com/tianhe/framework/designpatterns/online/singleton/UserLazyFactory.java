package com.tianhe.framework.designpatterns.online.singleton;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserLazyFactory {

    private static User instance;

    public User getInstance() {
        if (null == instance) {
            synchronized (User.class) {
                if (null == instance) {
                    instance = new User();
                }
            }
        }
        return instance;
    }
}
