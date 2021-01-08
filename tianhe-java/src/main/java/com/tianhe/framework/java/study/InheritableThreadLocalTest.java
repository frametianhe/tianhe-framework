package com.tianhe.framework.java.study;

import java.util.concurrent.TimeUnit;

/**
 * InheritableThreadLocal不是线程安全的。和线程池集合使用，线程复用导致线程安全问题，可以get后remove掉
 * 阿里开源TransmittableThreadLocal 解决了这个原理
 * Created by tianhe on 2020/1/2.
 */
public class InheritableThreadLocalTest {

    public static ThreadLocal<Integer> inheritableThreadLocal = new InheritableThreadLocal<>();

    public static void main(String[] args) {
        inheritableThreadLocal.set(123);
        new Thread(){
            @Override
            public void run() {
                System.out.println(inheritableThreadLocal.get());
            }
        }.start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.err.println(inheritableThreadLocal.get());
    }
}
