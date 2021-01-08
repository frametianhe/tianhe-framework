package com.tianhe.framework.designpatterns.online.singleton;

public class UserStaticFactory {

    private static class UserHolder {
        private static User instance;

        static {
            instance = new User();
        }
    }

    public static User getInstance() {
        return UserHolder.instance;
    }
}
