package com.tianhe.framework.disruptor.study.procon;

import com.lmax.disruptor.EventFactory;

/**
 * 功能说明：事件工厂
 *
 * @author:weifeng.jiang
 * @DATE:2017/5/18 @TIME:18:51
 */
public class SimpleEventFactory implements EventFactory<SimpleEvent> {

    @Override
    public SimpleEvent newInstance() {
        return new SimpleEvent();
    }
}
