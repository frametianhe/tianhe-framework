package com.tianhe.framework.designpatterns.study.interceptor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: he.tian
 * @time: 2019-03-11 17:21
 */
public class FilterChain {

    private List<Filter> filters = new ArrayList<>();
    private Target target;

    public void addFilter(Filter filter){
        filters.add(filter);
    }

    public void execute(String request){
        for (Filter filter : filters) {
            filter.execute(request);
        }
        target.execute(request);
    }

    public void setTarget(Target target) {
        this.target = target;
    }
}
