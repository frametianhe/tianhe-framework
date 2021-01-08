package com.tianhe.framework.disruptor.study.procon;
import com.lmax.disruptor.WorkHandler;

import java.util.UUID;

/**
 * 事件处理对象 消费者
 */
  
  
public class TradeTransactionInDBHandler implements WorkHandler<TradeTransaction>{  
  
    @Override  
    public void onEvent(TradeTransaction event) throws Exception {  
        event.setId(UUID.randomUUID().toString());  
        System.out.println(event.getId());  
          
    }  
  
}  