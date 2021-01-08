package com.tianhe.framework.disruptor.study.procon;

        import com.lmax.disruptor.EventHandler;

/**
 * 事件处理对象 消费者
 * @author:weifeng.jiang
 * @DATE:2017年1月1日 @TIME: 下午3:43:50
 */
public class TradeTransactionJMSNotifyHandler implements EventHandler<Trade> {

    @Override
    public void onEvent(Trade event, long sequence,boolean endOfBatch) throws Exception {
        System.out.println("do something jms message");
    }
}