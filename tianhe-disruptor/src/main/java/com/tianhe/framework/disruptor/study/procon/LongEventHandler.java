package com.tianhe.framework.disruptor.study.procon;

import com.lmax.disruptor.EventHandler;

public class LongEventHandler implements EventHandler<LongEvent>
{

    private int index;//消费者id

    public LongEventHandler(int i){
        this.index = i;
    }

    public LongEventHandler(){}


    public void onEvent(LongEvent event, long sequence, boolean endOfBatch) throws InterruptedException {
//        System.out.println(String.format("sequence=%s,event=%s,index=================%s",sequence,event.getValue(),index));
        System.out.println(String.format("sequence=%s,event=%s,index=================%s",sequence,event.getValue(),index));
    }
}