package com.tianhe.framework.designpatterns.study.interceptor;

/**
 * @author: he.tian
 * @time: 2019-03-11 17:26
 */
public class Client {

    private FilterManager filterManager;

    public void setFilterManager(FilterManager filterManager) {
        this.filterManager = filterManager;
    }

    public void sendRequest(String request){
        filterManager.filterRequest(request);
    }
}
