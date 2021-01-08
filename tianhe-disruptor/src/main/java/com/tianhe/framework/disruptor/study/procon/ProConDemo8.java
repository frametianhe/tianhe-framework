package com.tianhe.framework.disruptor.study.procon;
import com.lmax.disruptor.*;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class ProConDemo8 {
	
    public static void main(String[] args) throws InterruptedException {  
    	
        final int bufferSize = 1024;  
        EventFactory<TradeTransaction> eventFactory = new EventFactory<TradeTransaction>() {
            public TradeTransaction newInstance() {  
                return new TradeTransaction();  
            }  
        };  
  
        RingBuffer<TradeTransaction> ringBuffer = RingBuffer.createSingleProducer(eventFactory, bufferSize);  
  
        SequenceBarrier sequenceBarrier = ringBuffer.newBarrier();  
  
        ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());  
  
        WorkHandler<TradeTransaction>[] workHandlers = new WorkHandler[3];//可变参数，可以使一个一个的参数，也可以是一个数组
        for (int i = 0; i < 3; i++) {  //workHandler小于等于线程池核心线程数
            WorkHandler<TradeTransaction> workHandler = new TradeTransactionInDBHandler();
            workHandlers[i] = workHandler;  
        }  
  
        WorkerPool<TradeTransaction> workerPool = new WorkerPool<TradeTransaction>(ringBuffer, sequenceBarrier, new IgnoreExceptionHandler(), workHandlers);  
  
        workerPool.start(executor);  
  
        //生产者
        for (int i = 0; i < 800; i++) {  
            long seq = ringBuffer.next();  
            ringBuffer.get(seq).setPrice(Math.random() * 9999);  
            ringBuffer.publish(seq);  
            if (i % 10 == 0) {  
                System.out.println(((ThreadPoolExecutor) executor).getActiveCount() + "===================");  
            }  
        }  
        
        workerPool.drainAndHalt();  
        executor.shutdown();  
    }  
}  