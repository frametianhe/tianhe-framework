package com.tianhe.framework.disruptor.study.procon;

import com.lmax.disruptor.dsl.Disruptor;

import java.util.concurrent.CountDownLatch;

/**
 * 生产者
 * @author:weifeng.jiang
 * @DATE:2017年1月1日 @TIME: 下午3:47:11
 */
public class TradeTransactionPublisher implements Runnable{  
	
    Disruptor<Trade> disruptor;  
    private CountDownLatch latch;  
    private static int LOOP=1000;//模拟一千万次交易的发生  
  
    public TradeTransactionPublisher(CountDownLatch latch,Disruptor<Trade> disruptor) {  
        this.disruptor=disruptor;  
        this.latch=latch;  
    }  
  
    @Override  
    public void run() {  
        TradeTransactionEventTranslator tradeTransloator=new TradeTransactionEventTranslator();  
        for(int i=0;i<LOOP;i++){  
            disruptor.publishEvent(tradeTransloator);  
        }  
        latch.countDown();  
    }  
      
}  