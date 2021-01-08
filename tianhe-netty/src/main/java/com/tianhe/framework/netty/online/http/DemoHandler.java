package com.tianhe.framework.netty.online.http;

/**
 * Created by tianhe on 2019/12/13.
 */
public class DemoHandler implements Handler{

    @Override
    public String handler(Request request) {
        System.out.println(request.toString());
        return "hello";
    }
}
