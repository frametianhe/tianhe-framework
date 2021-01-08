package com.tianhe.framework.disruptor.study.procon;

import com.lmax.disruptor.EventHandler;

/**
 * 消费者3
 * @author:weifeng.jiang
 * @DATE:2017年1月1日 @TIME: 下午3:43:50
 */
public class TradeEventThreeHandler implements EventHandler<Trade> {
  
    @Override  
    public void onEvent(Trade event, long sequence,boolean endOfBatch) throws Exception {  
        System.out.println(Thread.currentThread().getName()+"      do something3,sequence="+sequence);
    }  
}  