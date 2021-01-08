package com.tianhe.framework.designpatterns.study.interceptor;

/**
 * @author: he.tian
 * @time: 2019-03-11 17:20
 */
public class DebugFilter implements Filter{

    @Override
    public void execute(String request) {
        System.out.println("debug request="+request);
    }
}
