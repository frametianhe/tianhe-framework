package com.tianhe.framework.designpatterns.online.singleton;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserEnumFactory {

    static enum SingletomEnum {

        INSTANCE;

        private User user;

        private SingletomEnum() {
            user = new User();
        }

        public User getInstance() {
            return user;
        }
    }

    public static User getInstance() {
        return SingletomEnum.INSTANCE.getInstance();
    }
}
