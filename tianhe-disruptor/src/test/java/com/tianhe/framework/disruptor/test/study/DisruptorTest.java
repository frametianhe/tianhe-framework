package com.tianhe.framework.disruptor.test.study;

import com.lmax.disruptor.RingBuffer;
import com.tianhe.framework.disruptor.study.procon.DisruptorExecutorService;
import com.tianhe.framework.disruptor.study.procon.User;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DisruptorTest{

	public static void main(String[] args) throws Exception {
		
		ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());  
		DisruptorExecutorService executorService = new DisruptorExecutorService(executor);
		long startTime = System.currentTimeMillis();
		RingBuffer ringBuffer = executorService.run();
		 //生产者
        for (int i = 0; i < 1000; i++) {  
            long seq = ringBuffer.next();  
            User user = (User)ringBuffer.get(seq);
            user.setName("name"+i);
            ringBuffer.publish(seq);  
        }  
        executorService.await();
//        JOptionPane.showMessageDialog(null, (System.currentTimeMillis()-startTime)+"ms");
        System.out.println((System.currentTimeMillis()-startTime)+"ms");
        //    次数                     DisruptorTest（ms）        CountDownLatchTest（ms）
        //    5000000       167248ms               182985ms
        //    2000000       67181ms                73577ms
        //    1000000       33137ms                36193ms
        //    500000        16257ms                17319ms
        //    100000        3092ms                 3325ms
        //    10000         374ms                  302ms
        //    5000          182ms                  130ms
        //    1000          101ms                  50ms
	}
}
