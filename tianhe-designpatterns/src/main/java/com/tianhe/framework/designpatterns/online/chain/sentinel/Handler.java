package com.tianhe.framework.designpatterns.online.chain.sentinel;

/**
 * Created by tianhe on 2019/11/6.
 */
public interface Handler<T> {

    void execute(T t);

    void fireExecute(Object obj);
}
