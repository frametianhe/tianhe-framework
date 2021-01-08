package com.tianhe.framework.designpatterns.study.interceptor;

/**
 * @author: he.tian
 * @time: 2019-03-11 17:19
 */
public class AuthenticationFilter implements Filter {

    @Override
    public void execute(String request) {
        System.out.println("authenticating request="+request);
    }
}
