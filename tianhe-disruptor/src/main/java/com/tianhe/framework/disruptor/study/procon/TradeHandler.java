package com.tianhe.framework.disruptor.study.procon;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.WorkHandler;

import java.util.UUID;

/**
 * 事件处理对象 消费者
 * @author:weifeng.jiang
 * @DATE:2017年1月1日 @TIME: 下午3:26:21
 */
public class TradeHandler implements EventHandler<Trade>,WorkHandler<Trade> {  
  
    @Override  
    public void onEvent(Trade event, long sequence,  
            boolean endOfBatch) throws Exception {  
        this.onEvent(event);  
        System.out.println(Thread.currentThread().getName());
    }  
  
    @Override  
    public void onEvent(Trade event) throws Exception {  
        //这里做具体的消费逻辑  
        event.setId(UUID.randomUUID().toString());//简单生成下ID  
        System.out.println(event.getId());  
        System.out.println(Thread.currentThread().getName());
    }  
}  
  