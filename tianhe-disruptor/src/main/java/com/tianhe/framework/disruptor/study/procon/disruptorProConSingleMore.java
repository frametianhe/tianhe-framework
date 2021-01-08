package com.tianhe.framework.disruptor.study.procon;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.YieldingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 生产者消费者模式，一对多，不适合主线程分发的场景，也就是线程池模式，只适合发布与订阅模式，多个消费者并发执行
 */
public class disruptorProConSingleMore
{

    public static RingBuffer<LongEvent> ringBuffer = null;
    public static ExecutorService customerExecutor = null;
    public static Disruptor<LongEvent> disruptor = null;

    static {

        // Executor that will be used to construct new threads for consumers
        customerExecutor = Executors.newCachedThreadPool();

        // The factory for the event
        LongEventFactory factory = new LongEventFactory();

        // Specify the size of the ring buffer, must be power of 2. RingBuffer 大小，必须是 2 的 N 次方；
        int bufferSize = 1024;

        // Construct the Disruptor.
        //disruptor = new Disruptor<>(factory, bufferSize, executor);
        disruptor =  new Disruptor<>(factory, bufferSize,customerExecutor, ProducerType.MULTI,new YieldingWaitStrategy());

        // Connect the handler
        disruptor.handleEventsWith(new LongEventHandler(1), new LongEventHandler(2), new LongEventHandler(3));

        // Start the Disruptor, starts all threads running
        disruptor.start();

        // Get the ring buffer from the Disruptor to be used for publishing.
        ringBuffer = disruptor.getRingBuffer();
    }

    public static void main(String[] args) {
        final  CountDownLatch latch = new CountDownLatch(10);
        final LongEventProducer producer = new LongEventProducer(ringBuffer);
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        try {
            for (long a = 0; a < 10; a ++){
                final long num = a;
                executorService.submit(new Runnable() {

                    @Override
                    public void run() {
                        producer.onData(num);
                        latch.countDown();
                    }
                });
            }

            latch.await();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(disruptor != null) disruptor.shutdown();//关闭 disruptor，方法会堵塞，直至所有的事件都得到处理；
            if(customerExecutor != null) customerExecutor.shutdown();//关闭 disruptor 使用的线程池；如果需要的话，必须手动关闭， disruptor 在 shutdown 时不会自动关闭；
            if(executorService != null) executorService.shutdown();//关闭 disruptor 使用的线程池；如果需要的话，必须手动关闭， disruptor 在 shutdown 时不会自动关闭；
        }

    }
}