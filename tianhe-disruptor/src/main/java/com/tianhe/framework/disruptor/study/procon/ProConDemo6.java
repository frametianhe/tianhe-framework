package com.tianhe.framework.disruptor.study.procon;

import com.lmax.disruptor.*;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ProConDemo6 {
	
    public static void main(String[] args) throws InterruptedException {  
        int bufferSize=1024;  
        EventFactory<Trade> eventFactory=new EventFactory<Trade>() {  
            public Trade newInstance() {  
                return new Trade();  
            }  
        };  
        RingBuffer<Trade> ringBuffer=RingBuffer.createSingleProducer(eventFactory, bufferSize);  
          
        SequenceBarrier sequenceBarrier = ringBuffer.newBarrier();  
          
        ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());  
          
        WorkHandler<Trade> workHandlers=new TradeHandler();  
        /* 
         * 这个类代码很简单的，亲自己看哈！~ 
         */  
        WorkerPool<Trade> workerPool=new WorkerPool<Trade>(ringBuffer, sequenceBarrier, new IgnoreExceptionHandler(), workHandlers);  
          
        workerPool.start(executor);  
          
        //下面这个生产8个数据，图简单就写到主线程算了  
        for(int i=0;i<8;i++){  
            long seq=ringBuffer.next();  
            ringBuffer.get(seq).setPrice(Math.random()*9999);  
            ringBuffer.publish(seq);  
        }  
          
        workerPool.drainAndHalt();  
        executor.shutdown();  
    }  
}  