package com.tianhe.framework.java.study.gdg;

import java.util.concurrent.*;

/**
 * callable实现任务分发，线程同步
 * weifeng.jiang 2018-06-22 15:24
 */
public class CallableTaskDemo {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i=0;i<10;i++){
            Future<String> future = executorService.submit(new Callable<String>() {
                @Override
                public String call() throws Exception {
                    return Thread.currentThread().getName();
                }
            });
            try {
                future.get();//阻塞
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        executorService.shutdown();
    }
}
