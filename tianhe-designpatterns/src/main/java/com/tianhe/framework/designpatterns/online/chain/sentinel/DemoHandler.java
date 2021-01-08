package com.tianhe.framework.designpatterns.online.chain.sentinel;

/**
 * Created by tianhe on 2019/11/6.
 */
public class DemoHandler extends AbstractLinkedHandler<Order>{

    @Override
    public void execute(Order order) {
        System.out.println("demoHandler execute");
        fireExecute(order);
    }
}
