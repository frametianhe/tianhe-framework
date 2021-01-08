package com.tianhe.framework.disruptor.study.procon;

import com.lmax.disruptor.BusySpinWaitStrategy;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import com.lmax.disruptor.util.DaemonThreadFactory;

/**
 * 生产者消费者模式，也就是发布订阅者模式,一个消费者执行
 * @author:weifeng.jiang
 * @DATE:2016年12月30日 @TIME: 下午1:08:15
 */
public class DisruptorProConSingleSingle {

    public static void main(String[] args) {

        long start = System.currentTimeMillis();
        SimpleEventFactory factory = new SimpleEventFactory();
        
        //创建disruptor
        final Disruptor<SimpleEvent> disruptor = new Disruptor<SimpleEvent>(factory,2<<10, DaemonThreadFactory.INSTANCE,
                ProducerType.SINGLE, new BusySpinWaitStrategy());//单生产者模式

         SimpleHandler  simpleHandler= new SimpleHandler();
        
        //绑定disruptor
        disruptor.handleEventsWith(simpleHandler);
        disruptor.start();
        
        new Thread(new Runnable() {

            @Override
            public void run() {
                RingBuffer<SimpleEvent> ringBuffer = disruptor.getRingBuffer();
                for (int i = 0; i < 10000000; i++) {
                	//获取下一个序号
                    long seq = ringBuffer.next();
                    SimpleEvent simpleEvent = ringBuffer.get(seq);
                    simpleEvent.setValue(i+"");
                    ringBuffer.publish(seq);//发布
                }
            }
        }).start();
        long end = System.currentTimeMillis();
        System.out.println((end - start) + " ms");
    }
}