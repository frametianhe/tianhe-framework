package com.tianhe.framework.designpatterns.study.interceptor;

/**
 * @author: he.tian
 * @time: 2019-03-11 17:24
 */
public class FilterManager {

    private FilterChain filterChain;

    public FilterManager(Target target){
        filterChain = new FilterChain();
        filterChain.setTarget(target);
    }

    public void setFilter(Filter filter){
        filterChain.addFilter(filter);
    }

    public void filterRequest(String request){
        filterChain.execute(request);
    }
}
