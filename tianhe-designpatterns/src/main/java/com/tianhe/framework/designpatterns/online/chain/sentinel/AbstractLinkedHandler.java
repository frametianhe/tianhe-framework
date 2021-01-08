package com.tianhe.framework.designpatterns.online.chain.sentinel;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by tianhe on 2019/11/6.
 */
public abstract class AbstractLinkedHandler<T> implements Handler<T>{

    @Getter
    @Setter
    private AbstractLinkedHandler<?> next;

    @Override
    public void fireExecute(Object obj) {
        if(next != null){
            next.transform(obj);
        }
    }

    void transform(Object obj){
        T t = (T) obj;
        execute(t);
    }
}
