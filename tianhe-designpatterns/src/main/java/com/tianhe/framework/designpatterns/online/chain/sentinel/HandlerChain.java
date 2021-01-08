package com.tianhe.framework.designpatterns.online.chain.sentinel;

/**
 * Created by tianhe on 2019/11/6.
 */
public abstract class HandlerChain extends AbstractLinkedHandler<Object>{

    public abstract void addFirst(AbstractLinkedHandler<?> linkedHandler);

    public abstract void addLast(AbstractLinkedHandler<?> linkedHandler);
}
