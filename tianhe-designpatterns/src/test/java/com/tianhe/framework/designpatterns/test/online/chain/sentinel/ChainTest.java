package com.tianhe.framework.designpatterns.test.online.chain.sentinel;

import com.tianhe.framework.designpatterns.online.chain.sentinel.DefaultHandlerChainBuilder;
import com.tianhe.framework.designpatterns.online.chain.sentinel.HandlerChain;
import com.tianhe.framework.designpatterns.online.chain.sentinel.Order;

/**
 * Created by tianhe on 2019/11/8.
 */
public class ChainTest {

    public static void main(String[] args) {
        DefaultHandlerChainBuilder builder = new DefaultHandlerChainBuilder();
        HandlerChain handlerChain = builder.build();
        handlerChain.execute(new Order());
    }
}
