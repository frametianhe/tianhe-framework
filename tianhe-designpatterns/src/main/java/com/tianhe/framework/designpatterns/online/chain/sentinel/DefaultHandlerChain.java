package com.tianhe.framework.designpatterns.online.chain.sentinel;

/**
 * Created by tianhe on 2019/11/6.
 */
public class DefaultHandlerChain extends HandlerChain{

    AbstractLinkedHandler<?> first = new AbstractLinkedHandler<Object>() {
        @Override
        public void execute(Object o) {
            super.fireExecute(o);
        }
    };

    AbstractLinkedHandler<?> end = first;

    @Override
    public void execute(Object o) {
        first.transform(o);
    }

    @Override
    public void addFirst(AbstractLinkedHandler<?> linkedHandler) {
        linkedHandler.setNext(first.getNext());
        first.setNext(linkedHandler);
        if(end == first){
            end = linkedHandler;
        }
    }

    @Override
    public void addLast(AbstractLinkedHandler<?> linkedHandler) {
        end.setNext(linkedHandler);
        end = linkedHandler;
    }

    @Override
    public void setNext(AbstractLinkedHandler<?> next) {
        addLast(next);
    }

    @Override
    public AbstractLinkedHandler<?> getNext() {
        return first.getNext();
    }
}
