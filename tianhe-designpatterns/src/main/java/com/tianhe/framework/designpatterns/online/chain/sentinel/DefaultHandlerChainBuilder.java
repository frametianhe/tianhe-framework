package com.tianhe.framework.designpatterns.online.chain.sentinel;

/**
 * Created by tianhe on 2019/11/6.
 */
public class DefaultHandlerChainBuilder implements HandlerChainBuilder{

    @Override
    public HandlerChain build() {
        HandlerChain chain = new DefaultHandlerChain();
        chain.addLast(new DemoHandler());
        chain.addLast(new TestHandler());
        return chain;
    }
}
