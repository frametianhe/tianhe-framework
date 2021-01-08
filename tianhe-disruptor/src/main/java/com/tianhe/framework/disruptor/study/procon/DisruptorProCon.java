package com.tianhe.framework.disruptor.study.procon;

import com.lmax.disruptor.BusySpinWaitStrategy;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import com.lmax.disruptor.util.DaemonThreadFactory;

/**
 * 一对一的生产者消费者模型
 * cas 无锁，cpu级别的命令，加锁是系统层面的命令，会有上下文切换的开销
 * 
 * 性能是LinkedBlockingQueue的3倍，官方提供数据是在5倍
 * 
 * 性能比较
 * a.阻塞队列中使用了锁，disruptor是无锁
 * b.disruptor避免伪共享，缓存系统中是以缓存行（cache line）为单位存储的。缓存行是2的整数幂个连续字节，
 * 一般为32-256个字节。最常见的缓存行大小是64个字节。当多线程修改互相独立的变量时，如果这些变量共享同一个缓存行，
 * 就会无意中影响彼此的性能，这就是伪共享
 * c.ringBuffer的使用
 * disruptor是用ringBuffer构造无锁队列，数组是预分配的，这样避免了Java GC带来的运行开销。生产者在生产消息或产生事件的时候对Ringbuffer元素中的属性进行更新，
 * 而不是替换Ringbuffer中的元素
 * 
 * @author:weifeng.jiang
 * @DATE:2016年12月30日 @TIME: 下午1:08:15
 */
public class DisruptorProCon {

    public static void main(String[] args) {
        LogEventFactory factory = new LogEventFactory();
        
        //创建disruptor
        final Disruptor<LogEvent> disruptor = new Disruptor<LogEvent>(factory,
                65536, DaemonThreadFactory.INSTANCE,
                ProducerType.SINGLE, new BusySpinWaitStrategy());//单生产者模式

        LogEventHandler logEventHandler = new LogEventHandler();
        
        //绑定disruptor
        disruptor.handleEventsWith(logEventHandler);
        disruptor.start();
        
        new Thread(new Runnable() {

            @Override
            public void run() {
                RingBuffer<LogEvent> ringBuffer = disruptor.getRingBuffer();
                for (int i = 0; i < 5000000; i++) {
                	//获取下一个序号
                    long seq = ringBuffer.next();
                    LogEvent logEvent = ringBuffer.get(seq);
                    logEvent.setLogId(i);
                    logEvent.setContent("c" + i);
                    ringBuffer.publish(seq);//发布
                }
            }
        }).start();
    }
}