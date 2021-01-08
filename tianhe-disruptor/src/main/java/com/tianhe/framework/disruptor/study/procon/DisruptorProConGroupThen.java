package com.tianhe.framework.disruptor.study.procon;

import com.lmax.disruptor.BusySpinWaitStrategy;
import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.EventHandlerGroup;
import com.lmax.disruptor.dsl.ProducerType;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 生产者消费者模式，也就是发布订阅者模式，不适合线程池模式，也就是主线程任务分发
 * 消费者1消费者2并发处理完，消费者3才执行
 */
public class DisruptorProConGroupThen {
	
    public static void main(String[] args) throws InterruptedException {  
    	
        long beginTime=System.currentTimeMillis();  
          
        int bufferSize=1024;  
        ExecutorService executor=Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        Disruptor<Trade> disruptor=new Disruptor<Trade>(new EventFactory<Trade>() {
            @Override  
            public Trade newInstance() {  
                return new Trade();  
            }  
        }, bufferSize, executor, ProducerType.SINGLE, new BusySpinWaitStrategy());
          
        //使用disruptor创建消费者组C1,C2  
        EventHandlerGroup<Trade> handlerGroup=disruptor.handleEventsWith(new TradeEventHandler(),new TradeEventTwoHandler());
          
        TradeEventThreeHandler tradeEventThreeHandler=new TradeEventThreeHandler();
        //声明在C1,C2完事之后执行JMS消息发送操作 也就是流程走到C3  
        handlerGroup.then(tradeEventThreeHandler);
          
          
        disruptor.start();//启动  
        CountDownLatch latch=new CountDownLatch(1);  
        //生产者准备  
        executor.submit(new TradeTransactionPublisher(latch, disruptor));  
        latch.await();//等待生产者完事.  
        disruptor.shutdown();  
        executor.shutdown();  
          
        System.out.println("总耗时:"+(System.currentTimeMillis()-beginTime));  
    }  
}  