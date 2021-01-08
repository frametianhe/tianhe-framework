package com.tianhe.framework.disruptor.study.procon;

import com.lmax.disruptor.EventTranslator;

import java.util.Random;

class TradeTransactionEventTranslator implements EventTranslator<Trade>{  
	
    private Random random=new Random();  
    @Override  
    public void translateTo(Trade event, long sequence) {  
        this.generateTradeTransaction(event);  
    }  
    private Trade generateTradeTransaction(Trade trade){  
        trade.setPrice(random.nextDouble()*9999);  
        return trade;  
    }  
}  