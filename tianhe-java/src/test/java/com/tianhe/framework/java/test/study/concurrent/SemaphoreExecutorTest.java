package com.tianhe.framework.java.test.study.concurrent;


import com.tianhe.framework.java.online.concurrent.SemaphoreExecutor;

/**
 * 功能说明：
 *
 * @author:weifeng.jiang
 * @DATE:2017/4/12 @TIME:16:47
 */
public class SemaphoreExecutorTest {

    public static void main(String[] args) throws Exception{
       final SemaphoreExecutor semaphoreExecutor = new SemaphoreExecutor(Runtime.getRuntime().availableProcessors(),0);
        for (int i=0;i<10;i++){
            semaphoreExecutor.run(new Runnable() {
                @Override
                public void run() {
                    System.out.println("测试"+Thread.currentThread().getName());
                }
            });
        }
        semaphoreExecutor.await();
    }
}
