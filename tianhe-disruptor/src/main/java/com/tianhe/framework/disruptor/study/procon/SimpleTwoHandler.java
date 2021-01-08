package com.tianhe.framework.disruptor.study.procon;
  
import com.lmax.disruptor.EventHandler;

/** 
 * 构建BatchEventProcessor 消费者
 */  
public class SimpleTwoHandler implements EventHandler<SimpleEvent> {

    @Override
    public void onEvent(SimpleEvent event, long sequence, boolean endOfBatch)throws Exception {

        //业务处理
        System.out.println(Thread.currentThread().getName()+"   SimpleTwoHandler    "+sequence);
    }

}  