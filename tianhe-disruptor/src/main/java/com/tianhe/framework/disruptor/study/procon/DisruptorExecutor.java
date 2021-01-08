package com.tianhe.framework.disruptor.study.procon;
import com.lmax.disruptor.*;

import java.lang.reflect.ParameterizedType;
import java.util.concurrent.ExecutorService;
  
public class DisruptorExecutor<T> {  
	
	private ExecutorService executor;
	private WorkerPool<T> workerPool;
	
	public DisruptorExecutor(ExecutorService executorService) {
		super();
		this.executor = executorService;
	}
	public DisruptorExecutor() {
		super();
	}
	
    public RingBuffer run() throws Exception {  
    	
        final int bufferSize = 1024;  
       final Class<T> classType = (Class<T>) ((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        EventFactory<T> eventFactory = new EventFactory<T>() {  
            public T newInstance() {  
                try {
					return (T) classType.newInstance();
				} catch (Exception e) {
					e.printStackTrace();
				}
                return null;
            }  
        };  
        RingBuffer<T> ringBuffer = RingBuffer.createSingleProducer(eventFactory, bufferSize);  
        SequenceBarrier sequenceBarrier = ringBuffer.newBarrier();  
        WorkHandler<T>[] workHandlers = new WorkHandler[3];//可变参数，可以使一个一个的参数，也可以是一个数组
        for (int i = 0; i < 3; i++) {  //workHandler小于等于线程池核心线程数
            WorkHandler<T> workHandler = new ConsuEventHandler<T>();  
            workHandlers[i] = workHandler;  
        }  
        workerPool = new WorkerPool<T>(ringBuffer, sequenceBarrier, new IgnoreExceptionHandler(), workHandlers);  
        workerPool.start(executor);   
        return ringBuffer;
    }

	public void await() {
		workerPool.drainAndHalt();  
        executor.shutdown();
	}
}  