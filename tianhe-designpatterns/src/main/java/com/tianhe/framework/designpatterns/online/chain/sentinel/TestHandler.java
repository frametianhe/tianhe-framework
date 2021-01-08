package com.tianhe.framework.designpatterns.online.chain.sentinel;

/**
 * Created by tianhe on 2019/11/8.
 */
public class TestHandler extends AbstractLinkedHandler<Order>{

    @Override
    public void execute(Order order) {
        System.out.println("testHandler execute");
        fireExecute(order);
    }
}
