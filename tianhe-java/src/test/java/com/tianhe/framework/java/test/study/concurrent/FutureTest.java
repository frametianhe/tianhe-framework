package com.tianhe.framework.java.test.study.concurrent;

import java.util.concurrent.*;

/**
 * Created by tianhe on 2019/9/29.
 */
public class FutureTest {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();

//        ConfigFuture configFuture = new ConfigFuture();
//        executor.execute(new Runnable() {
//            @Override
//            public void run() {
//                configFuture.setData("hello");
//            }
//        });
//        System.out.println(configFuture.get());

        CompletableFuture completableFuture = new CompletableFuture();
        executor.execute(new Runnable() {
            @Override
            public void run() {
                completableFuture.complete("hello");
            }
        });
        try {
            Object result = completableFuture.get(5000, TimeUnit.MILLISECONDS);
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
